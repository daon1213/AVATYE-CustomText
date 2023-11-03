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

        // CustomText 커스텀 뷰를 가져와 사용할 수 있습니다.
        val customText1 = binding.customText1

        val customText2 = binding.customText2

        val customText3 = binding.customText3

    }
}