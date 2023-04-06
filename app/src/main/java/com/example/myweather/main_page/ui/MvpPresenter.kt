package com.example.myweather.main_page.ui

interface MvpPresenter<V : MvpView> {
    fun attach(view: V)
    fun detach()
}