package com.example.myweather.main_page.ui

import androidx.annotation.StringRes

interface MvpView {
    fun showErrorMessage(t: Throwable? = null)
//    fun showErrorMessage(@StringRes messageRes: Int)
}