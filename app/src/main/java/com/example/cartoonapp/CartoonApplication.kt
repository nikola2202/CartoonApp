package com.example.cartoonapp

import android.app.Application
import android.content.Context

class CartoonApplication: Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

}