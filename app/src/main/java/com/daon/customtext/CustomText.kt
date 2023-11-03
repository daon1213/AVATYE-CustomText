package com.daon.customtext

import android.app.AlertDialog
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.daon.customtext.databinding.CustomTextLayoutBinding

class CustomText(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val binding: CustomTextLayoutBinding

    init {
        binding = CustomTextLayoutBinding.inflate(LayoutInflater.from(context), this)
        binding.settingsButton.setOnClickListener {

            binding.mainTextView.visibility = View.GONE
            binding.subTextView.visibility = View.GONE
            binding.settingsButton.visibility = View.GONE
            binding.editText.visibility = View.VISIBLE
            binding.subEditText.visibility = View.VISIBLE
            binding.confirmButton.visibility = View.VISIBLE

        }
        binding.confirmButton.setOnClickListener {
            val editTextContent = binding.editText.text.toString().trim()
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
                binding.mainTextView.text = binding.editText.text
                binding.subTextView.text = binding.subEditText.text
                // 어떠한 다이얼로그도 표시되지 않는 경우
                binding.mainTextView.visibility = View.VISIBLE
                binding.subTextView.visibility = View.VISIBLE
                binding.settingsButton.visibility = View.VISIBLE
                binding.editText.visibility = View.GONE
                binding.subEditText.visibility = View.GONE
                binding.confirmButton.visibility = View.GONE
            }
        }
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
}
