package com.example.geoquiz

import androidx.lifecycle.ViewModel

class QuisViweModel:ViewModel() {
     val questionbank = listOf(
        Question(R.string.question_sanaa,true,2),
        Question(R.string.question_taiz,true,2),
        Question(R.string.question_hodaidah,false,2),
        Question(R.string.question_ibb,true,2)
    )
    var currentIndex=0
    val currentQuestonAnswer:Boolean
    get()=questionbank[currentIndex].answer
    val currentQuestonText:Int
    get()=questionbank[currentIndex].restTextid
    val nextquestion: Int
        get()=questionbank[currentIndex+1].answered
    val pravquestion:Int
        get()=questionbank[currentIndex-1].answered
    fun moveToNext(){

        currentIndex=(currentIndex+1)%questionbank.size
    }
    fun pravToBack(){
        if (currentIndex==0)
        {
            currentIndex=questionbank.lastIndex
        }
        currentIndex=(currentIndex-1)%questionbank.size

    }


}