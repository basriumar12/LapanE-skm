package com.example.lapane_skm.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lapane_skm.R
import com.example.lapane_skm.save.*
import com.example.lapane_skm.ui.question.questionsatu.QuestionSatuActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_mulai_survey.setOnClickListener {

            this?.let { PrefSaveQuestionSatu(it).setData("null") }
            this?.let { PrefSaveQuestionDua(it).setData("null") }
            this?.let { PrefSaveQuestionTiga(it).setData("null") }
            this?.let { PrefSaveQuestionEmpat(it).setData("null") }
            this?.let { PrefSaveQuestionLima(it).setData("null") }
            this?.let { PrefSaveQuestion6(it).setData("null") }
            this?.let { PrefSaveQuestion7(it).setData("null") }
            this?.let { PrefSaveQuestion8(it).setData("null") }
            this?.let { PrefSaveQuestion9(it).setData("null") }
            this?.let { PrefSaveQuestion10(it).setData("null") }
            this?.let { PrefSaveQuestion11(it).setData("null") }
            this?.let { PrefSaveQuestion12(it).setData("null") }
            this?.let { PrefSaveQuestion13(it).setData("null") }
            this?.let { PrefSaveQuestion14(it).setData("null") }
            intentTo(QuestionSatuActivity::class.java)
        }
    }
}
