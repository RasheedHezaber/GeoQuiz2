package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val quizviwemodel: QuisViweModel by lazy {
        ViewModelProviders.of(this).get(QuisViweModel::class.java)
    }
    private lateinit var truebutton: Button
    private lateinit var falsebutton: Button
    private lateinit var nextbutton: ImageButton
    private lateinit var prevbutton: ImageButton
    private lateinit var questionTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        truebutton = findViewById(R.id.t_button)
        falsebutton = findViewById(R.id.f_button)
        questionTextView = findViewById(R.id.q_textview)
        nextbutton = findViewById(R.id.next_button)
        prevbutton = findViewById(R.id.back_bottom)

        truebutton.setOnClickListener {

            checkanswer(true)
        }

        falsebutton.setOnClickListener {

            checkanswer(false)

        }

        val tv_click_me = findViewById(R.id.q_textview) as TextView

        tv_click_me.setOnClickListener {
            quizviwemodel.moveToNext()

            updatequestion()
        }

        prevbutton.setOnClickListener {
quizviwemodel.pravToBack()
            updatequestion()
        }
        updatequestion()

        nextbutton.setOnClickListener {
quizviwemodel.moveToNext()
            updatequestion()
        }
    }

    fun updatequestion() {
        val questiontext = quizviwemodel.currentQuestonText
        questionTextView.setText(questiontext)

        if(quizviwemodel.questionbank[quizviwemodel.currentIndex].answered <= 1){
            truebutton?.isEnabled = false
            falsebutton?.isEnabled = false
        }
        else{
            truebutton?.isEnabled = true
            falsebutton?.isEnabled = true
        }

        if(showFinalScore() != 0){
            var scorePercent =Math.round((showFinalScore().toFloat() / quizviwemodel.questionbank.size.toFloat()) * 100.0f);
            Toast.makeText(this, "Your Final Score = " + showFinalScore() +"\n "
                    +scorePercent + "%", Toast.LENGTH_SHORT)
                .show()
        }


    }

    fun checkanswer(useranswer: Boolean) {

        val correctAnswe = quizviwemodel.currentQuestonAnswer

        val messageResId = if (useranswer == correctAnswe) {
            quizviwemodel.questionbank[quizviwemodel.currentIndex].answered = 1
            R.string.correct_toast
        } else {
            quizviwemodel.questionbank[quizviwemodel.currentIndex].answered = 0
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

        truebutton?.isEnabled = false
        falsebutton?.isEnabled = false
    }

    fun showFinalScore(): Int {
        var mScore = 0;
        for (n in quizviwemodel.questionbank) {
            if (n.answered == 2) {
                mScore = 0;
                break
            } else {
                mScore += n.answered
            }
        }
        return mScore;
    }
}




