package com.daon.customtext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.daon.customtext.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val customText1 = binding.customText1
        val customText2 = binding.customText2
        val customText3 = binding.customText3

        // mainTextViewText 및 subTextViewText를 사용하여 텍스트 설정
        customText1.mainTextViewText = "이 동물을 소개합니다!"
        customText1.subTextViewText = "- 기니피그"

        // mainTextViewText 및 subTextViewText를 사용하여 텍스트 가져오기
        customText1.mainTextViewText
        customText1.subTextViewText

        // ImageView에 이미지 리소스 설정/
        customText1.setImageViewResource(R.drawable.ic_guinea)

        // ImageView의 이미지 리소스 가져오기
        customText1.imageViewResource

        customText2.mainTextViewText = "이 동물을 소개합니다!"
        customText2.subTextViewText = "- 판다"

        customText2.mainTextViewText
        customText2.subTextViewText

        customText2.setImageViewResource(R.drawable.ic_panda)

        customText2.imageViewResource

        customText3.mainTextViewText = "이 동물을 소개합니다!"
        customText3.subTextViewText = "- 레서판다"

        customText3.mainTextViewText
        customText3.subTextViewText

        customText3.setImageViewResource(R.drawable.ic_lesser)

        customText3.imageViewResource
    }
}


