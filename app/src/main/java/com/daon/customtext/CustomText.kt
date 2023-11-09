package com.daon.customtext

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import com.daon.customtext.databinding.CustomTextLayoutBinding

@SuppressLint("CustomViewStyleable", "ClickableViewAccessibility")
class CustomText(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private val binding: CustomTextLayoutBinding
    private val fontResourceId: Int

    init {
        binding = CustomTextLayoutBinding.inflate(LayoutInflater.from(context), this)

        // attrs.xml에서 정의한 속성 가져오기
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView)

        // textSize 속성 가져오기
        val MaintextSize = typedArray.getDimension(R.styleable.CustomTextView_maintextSize, 18f)

        val SubtextSize = typedArray.getDimension(R.styleable.CustomTextView_subtextSize, 14f)

        // textStyle 속성 가져오기
        val MaintextStyle = typedArray.getInt(R.styleable.CustomTextView_maintextStyle, 1)

        val MainEdittextStyle = typedArray.getInt(R.styleable.CustomTextView_maintextStyle, 1)

        val SubtextStyle = typedArray.getInt(R.styleable.CustomTextView_subtextStyle, 0)

        val SubEdittextStyle = typedArray.getInt(R.styleable.CustomTextView_subtextStyle, 0)

        // 폰트 파일 가져오기
        fontResourceId = typedArray.getResourceId(R.styleable.CustomTextView_textFont, 0)
        val typeface: Typeface? = if (fontResourceId != 0) {
            ResourcesCompat.getFont(context, fontResourceId)
        } else {
            val customTypeface = Typeface.createFromAsset(context.assets, "font/taebaekmilkyway.otf")
            customTypeface
        }

        // textSize와 textStyle 적용
        binding.mainTextView.textSize = MaintextSize
        binding.mainEditText.textSize = MaintextSize
        binding.subTextView.textSize = SubtextSize
        binding.subEditText.textSize = SubtextSize

        when (MaintextStyle) {
            1 -> binding.mainTextView.setTypeface(null, Typeface.BOLD)
            2 -> binding.mainTextView.setTypeface(null, Typeface.ITALIC)
            else -> binding.mainTextView.setTypeface(null, Typeface.NORMAL)
        }
        when (MainEdittextStyle) {
            1 -> binding.mainEditText.setTypeface(null, Typeface.BOLD)
            2 -> binding.mainEditText.setTypeface(null, Typeface.ITALIC)
            else -> binding.mainEditText.setTypeface(null, Typeface.NORMAL)
        }
        when (SubtextStyle) {
            1 -> binding.subTextView.setTypeface(null, Typeface.BOLD)
            2 -> binding.subTextView.setTypeface(null, Typeface.ITALIC)
            else -> binding.subTextView.setTypeface(null, Typeface.NORMAL)
        }
        when (SubEdittextStyle) {
            1 -> binding.subEditText.setTypeface(null, Typeface.BOLD)
            2 -> binding.subEditText.setTypeface(null, Typeface.ITALIC)
            else -> binding.subEditText.setTypeface(null, Typeface.NORMAL)
        }

        binding.subTextView.typeface = typeface
        binding.subEditText.typeface = typeface

        typedArray.recycle()

        binding.settingsButton.setOnClickListener {

            Log.d("settingButton", "settingButton이 터치되었습니다. 텍스트를 수정하세요.")

            binding.mainTextView.visibility = View.GONE
            binding.subTextView.visibility = View.GONE
            binding.settingsButton.visibility = View.GONE
            binding.mainEditText.visibility = View.VISIBLE
            binding.subEditText.visibility = View.VISIBLE
            binding.confirmButton.visibility = View.VISIBLE
        }

        binding.confirmButton.setOnClickListener {

            Log.d("confirmButton", "confirmButton이 터치되었습니다. 텍스트 수정이 완료되었습니다.")

            val editTextContent = binding.mainEditText.text.toString().trim()
            val subEditTextContent = binding.subEditText.text.toString().trim()

            if (editTextContent.isEmpty() && subEditTextContent.isEmpty()) {
                // EditText와 SubEditText 둘 다 공란인 경우
                showAlertDialog("제목과 내용을 입력해주세요")
            } else if (editTextContent.isEmpty()) {
                // EditText만 공란인 경우
                showAlertDialog("제목을 입력해주세요")
            } else if (subEditTextContent.isEmpty()) {
                // SubEditText만 공란인 경우
                showAlertDialog("내용을 입력해주세요")
            } else {
                binding.mainTextView.text = binding.mainEditText.text
                binding.subTextView.text = binding.subEditText.text

                // 어떠한 다이얼로그도 표시되지 않는 경우
                binding.mainTextView.visibility = View.VISIBLE
                binding.subTextView.visibility = View.VISIBLE
                binding.settingsButton.visibility = View.VISIBLE
                binding.mainEditText.visibility = View.GONE
                binding.subEditText.visibility = View.GONE
                binding.confirmButton.visibility = View.GONE

                // 확인 버튼을 누른 후 다이얼로그를 표시
                showTextModifiedDialog("텍스트가 수정되었습니다")
            }
        }

        // 터치 이벤트 리스너 추가
        setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.d("CustomText", "CustomText가 터치되었습니다.")
                    Log.d("CustomText", "MainTextView: ${binding.mainTextView.text}")
                    Log.d("CustomText", "SubTextView: ${binding.subTextView.text}")
                    Log.d("CustomText", "ImageViewResource: ${imageViewResource}")
                    true
                }
                else -> false
            }
        }
    }

    // mainTextView에 대한 getter 및 setter
    var mainTextViewText: String
        get() = binding.mainTextView.text.toString()
        set(value) {
            binding.mainTextView.text = value
        }

    // subTextView에 대한 getter 및 setter
    var subTextViewText: String
        get() = binding.subTextView.text.toString()
        set(value) {
            binding.subTextView.text = value
        }

    // ImageView에 대한 getter
    val imageViewResource: Int
        get() = binding.imageView.tag as? Int ?: 0

    // ImageView에 대한 setter
    fun setImageViewResource(resourceId: Int) {
        binding.imageView.setImageResource(resourceId)
        binding.imageView.tag = resourceId
    }

    private fun showAlertDialog(message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("알림")
            .setMessage(message)
            .setPositiveButton("확인") { dialog, _ ->
                dialog.dismiss()
            }
        val dialog = builder.create()
        dialog.show()
    }

    private fun showTextModifiedDialog(message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("알림")
            .setMessage(message)
            .setPositiveButton("확인") { dialog, _ ->
                dialog.dismiss()
            }
        val dialog = builder.create()
        dialog.show()
    }
}