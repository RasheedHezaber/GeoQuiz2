package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var truebutton:Button
    private lateinit var falsebutton:Button
    private lateinit var nextbutton:Button
    private lateinit var prevbutton:Button
    private lateinit var questionTextView:TextView
    private val questionbank = listOf(
        Question(R.string.question_sanaa,true),
       Question(R.string.question_taiz,true),
        Question(R.string.question_hodaidah,false),
            Question(R.string.question_ibb,true)
    )
    private var currentIndex=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        truebutton=findViewById(R.id.t_button)
        falsebutton=findViewById(R.id.f_button)
        questionTextView=findViewById(R.id.q_textview)
       nextbutton =findViewById(R.id.next_button)
       prevbutton =findViewById(R.id.back_bottom)

        truebutton.setOnClickListener {

              checkanswer(true)
        }

        falsebutton.setOnClickListener {

        checkanswer(false)

        }

        val tv_click_me = findViewById(R.id.q_textview) as TextView

        tv_click_me.setOnClickListener {
            currentIndex=(currentIndex+1)%questionbank.size

            updatequestion()
        }

        prevbutton.setOnClickListener {
            if (currentIndex==0){
                currentIndex=questionbank.lastIndex
            }
          currentIndex=(currentIndex-1)% questionbank.size


            updatequestion()
        }
            updatequestion()

        nextbutton.setOnClickListener {
            currentIndex=(currentIndex+1)%questionbank.size


            updatequestion()
        }    }

    fun updatequestion(){
        val questiontext=questionbank[currentIndex].restTextid
        questionTextView.setText(questiontext)

    }
    fun checkanswer(useranswer:Boolean){
       /* truebutton.isEnabled=false
        falsebutton.isEnabled=false
        questionbank[currentIndex].answered=true*/
        val correctAnswe=questionbank[currentIndex].answer

     val messageResId= if (correctAnswe==useranswer)
        {
            R.string.correct_toast

        }
        else {
            R.string.incorrect_toast

        }


        Toast.makeText(this,messageResId,Toast.LENGTH_LONG).show()

        }

                  }




