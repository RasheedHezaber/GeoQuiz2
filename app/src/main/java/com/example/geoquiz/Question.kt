package com.example.geoquiz

import androidx.annotation.StringRes

data class Question(@StringRes val restTextid:Int, var answer:Boolean,var answered:Int){
    annotation class StringRes



}