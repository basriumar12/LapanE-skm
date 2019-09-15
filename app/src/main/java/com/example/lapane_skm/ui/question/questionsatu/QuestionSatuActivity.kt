package com.example.lapane_skm.ui.question.questionsatu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lapane_skm.R
import com.example.lapane_skm.save.*
import com.example.lapane_skm.ui.main.BaseActivity
import com.example.lapane_skm.ui.main.MainActivity
import com.example.lapane_skm.ui.question.fragment_dua.QuestionDuaFragment
import kotlinx.android.synthetic.main.activity_question_satu.*
import kotlinx.android.synthetic.main.layout_recyclerview_dialog.*

class QuestionSatuActivity : BaseActivity() {

    var isDataQuestion : String? = null
    private var savedInstanceState: Bundle? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_satu)

        tv_silahkan_pilih_question.text = "Silahkan Pilih satuan Kerja"


        tv_silahkan_pilih_question.setOnClickListener {
            listDataQuestion()

        }

        isDataQuestion = PrefSaveQuestionSatu(this).getData().toString()

        if (isDataQuestion == null){

        }
        tv_next.visibility = View.GONE
        tv_prev.visibility = View.GONE
        tv_next.setOnClickListener {
            openFragment(QuestionDuaFragment())


        }
    }

    private fun listDataQuestion() {

        var data: List<ModelSatuanKerja> =
            listOf(
                ModelSatuanKerja(
                    "1",
                    "Biro Kerjasama, Humas, dan Umum, Jakarta"
                ),
                ModelSatuanKerja(
                    "2",
                    "Pusat Pemanfaatan Penginderaan Jauh, Jakarta"
                ),
                ModelSatuanKerja(
                    "3",
                    "Pusat Teknologi dan Data Penginderaan Jauh, Jakarta"
                ),
                ModelSatuanKerja(
                    "4",
                    "Pusat Sains dan Teknologi Atmosfer, Bandung "
                ),
                ModelSatuanKerja("5", "Pusat Sains Antariksa, Bandung"),
                ModelSatuanKerja(
                    "6",
                    "Pusat Teknologi Penerbangan, Bogor "
                ),
                ModelSatuanKerja("7", "Pusat Teknologi Roket, Bogor "),
                ModelSatuanKerja(
                    "8",
                    "Pusat Teknologi Satelit, Bogor "
                ),
                ModelSatuanKerja(
                    "9",
                    "Pusat Teknologi Informasi dan Komunikasi Penerbangan dan Antariksa, Jakarta "
                ),
                ModelSatuanKerja(
                    "10",
                    "Balai Pengamatan Antariksa dan Atmosfer, Pasuruan "
                ),
                ModelSatuanKerja(
                    "11",
                    "Balai Pengamatan Antariksa dan Atmosfer, Sumedang "
                ),
                ModelSatuanKerja(
                    "12",
                    "Stasiun Bumi Penginderaan Jauh, Parepare "
                ),
                ModelSatuanKerja(
                    "13",
                    "Balai Kendali Satelit, Pengamatan Antariksa dan Atmosfer dan Penginderaan Jauh, Biak"
                ),
                ModelSatuanKerja(
                    "14",
                    "Balai Uji Teknologi dan Pengamatan Antariksa dan Atmosfer, Garut  "
                ),
                ModelSatuanKerja(
                    "15",
                    "Balai Pengamatan Antariksa dan Atmosfer, Agam"
                ),
                ModelSatuanKerja(
                    "16",
                    "Balai Pengamatan Antariksa dan Atmosfer, Pontianak "
                )
            )


        val mDialogView = LayoutInflater.from(this).inflate(R.layout.layout_recyclerview_dialog, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("Pilih Satuan Kerja")
        //show dialog
        val mAlertDialog = mBuilder.show()

        val rvSatuanKerja = mAlertDialog.rv_lokasi_satuan_kerja
        rvSatuanKerja.layoutManager = LinearLayoutManager(this)
        val adapterSatuanKerja = SatuanKerjaAdapter(this, data)
        rvSatuanKerja.adapter = adapterSatuanKerja
        adapterSatuanKerja.notifyDataSetChanged()

        adapterSatuanKerja.setOnItemClickListener(object :
            SatuanKerjaAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {

                 mAlertDialog.dismiss()
                tv_silahkan_pilih_question.text = data.get(position).title.toString()
                PrefSaveQuestionSatu(this@QuestionSatuActivity).setData(data.get(position).id)
                tv_next.visibility = View.VISIBLE

            }


        })









    }



    public fun openFragment(fragment: Fragment) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.root_question_satu, fragment, fragment.javaClass.simpleName)
                .commit()
        }
    }
}
