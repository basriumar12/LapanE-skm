package com.example.lapane_skm.ui.question.questiondua

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.lapane_skm.R
import com.example.lapane_skm.model.ResponseSumbitSurvey
import com.example.lapane_skm.network.ApiConfig
import com.example.lapane_skm.ui.main.BaseActivity
import kotlinx.android.synthetic.main.activity_question_dua.*
import org.jetbrains.anko.ctx
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class QuestionDuaActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_dua)

        submit.setOnClickListener {
            submitData()
        }
    }

    private fun submitData() {
        ApiConfig().instance().postData(
            1,
            1,
            "1",
            "1",
            "1",
            "1",
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1

        ).enqueue(object : retrofit2.Callback<ResponseSumbitSurvey> {

            override fun onFailure(call: Call<ResponseSumbitSurvey>, t: Throwable) {
                Log.e("tag", "gagal ${t.message}")
            }

            override fun onResponse(call: Call<ResponseSumbitSurvey>, response: Response<ResponseSumbitSurvey>) {
                Log.e("tag", "sukses ${response.isSuccessful}")

                if (response.isSuccessful) {
                    val builder = AlertDialog.Builder(this@QuestionDuaActivity)
                        builder.setTitle("Info ")
                        builder.setMessage("anda tidak aktif internet, please aktifkan intenet !!")
                        builder.setPositiveButton("Ok"){ _,_ ->
                            finish()
                        builder.show()
                    }


                }
            }
        })
    }
}
