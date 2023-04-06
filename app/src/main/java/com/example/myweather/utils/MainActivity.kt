package com.example.myweather.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myweather.BlankFragment
import com.example.myweather.R
import com.example.myweather.databinding.ActivityMain2Binding
import com.example.myweather.main_page.ui.BaseActivity

class MainActivity : BaseActivity() {
    private lateinit var binding:ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val fragment = BlankFragment()
        changeFragment(fragment, R.id.mainActivity)
    }
}