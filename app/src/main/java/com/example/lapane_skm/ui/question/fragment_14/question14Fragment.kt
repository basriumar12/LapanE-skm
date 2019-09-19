package com.example.lapane_skm.ui.question.fragment_14

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.lapane_skm.R
import com.example.lapane_skm.constant.Constant
import com.example.lapane_skm.model.ResponseSumbitSurvey
import com.example.lapane_skm.network.ApiConfig
import com.example.lapane_skm.network.ApiInterface
import com.example.lapane_skm.network.ServiceGenerator
import com.example.lapane_skm.save.*
import com.example.lapane_skm.ui.main.MainActivity
import com.example.lapane_skm.ui.question.BaseFragment
import com.example.lapane_skm.ui.question.fragment_10.Question10Fragment
import com.example.lapane_skm.ui.question.fragment_11.Question11Fragment
import com.example.lapane_skm.ui.question.fragment_13.Question13Fragment
import com.example.lapane_skm.ui.question.fragment_6.Question6Fragment
import com.example.lapane_skm.ui.question.fragment_7.Question7Fragment
import com.example.lapane_skm.ui.question.fragment_8.Question8Fragment
import com.example.lapane_skm.ui.question.fragment_9.Question9Fragment
import com.example.lapane_skm.ui.question.fragment_dua.JabawanDuaAdapter
import com.example.lapane_skm.ui.question.questionsatu.ModelSatuanKerja
import com.example.lapane_skm.ui.question.questionsatu.QuestionSatuActivity
import com.example.lapane_skm.ui.question.fragment_tiga.QuestionTigaFragment
import kotlinx.android.synthetic.main.activity_question_satu.*
import kotlinx.android.synthetic.main.fragment_question_dua.view.*
import retrofit2.Call
import retrofit2.Response
import kotlin.system.exitProcess

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [QuestionDuaFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 *
 */
class Question14Fragment : BaseFragment() {
    private var listener: OnFragmentInteractionListener? = null
    var tvNo: TextView? = null
    var tvQuestion: TextView? = null
    var tvNext: TextView? = null
    var tvprev: TextView? = null
    var rvDataQuestion: RecyclerView? = null
    private var savedInstanceState: Bundle? = null

    var isDataQuestion: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_question_dua, container, false)
        tvNo = view.tv_no_question
        tvQuestion = view.tv_question
        tvNext = view.tv_next
        tvprev = view.tv_prev
        rvDataQuestion = view.rv_data_jawaban
        rvDataQuestion?.layoutManager = GridLayoutManager(activity, 2)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()

        isDataQuestion = activity?.let { PrefSaveQuestion14(it).getData().toString() }.toString()
        Log.e("data ", "data question 14 $isDataQuestion")

        val validasiData = isDataQuestion.toString()
        if (!validasiData.equals("null")) {
            tvNext?.visibility = View.VISIBLE
        } else {
            tvNext?.visibility = View.GONE
        }

        tv_prev.setOnClickListener {
            openFragment(Question13Fragment())
        }



        tvNext?.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)
            builder.setTitle("Info")
            builder.setMessage("Apakah pertanyaan yang di jawab sudah sesuai ?")
            //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
              submitData()
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->

            }


            builder.show()




        }
    }

    private fun submitData() {
        var sator = activity?.let { PrefSaveQuestionSatu(it).getData()?.toInt() }
        var layanan = activity?.let { PrefSaveQuestionDua(it).getData()?.toInt() }
        var usia = activity?.let { PrefSaveQuestionTiga(it).getData() }
        var gender = activity?.let { PrefSaveQuestionEmpat(it).getData() }
        var pendidikan = activity?.let { PrefSaveQuestionLima(it).getData() }
        var perkerjaan = activity?.let { PrefSaveQuestion6(it).getData() }
        var persyaratan = activity?.let { PrefSaveQuestion7(it).getData()?.toInt() }
        var smp = activity?.let { PrefSaveQuestion8(it).getData()?.toInt() }
        var waktu = activity?.let { PrefSaveQuestion9(it).getData()?.toInt() }
        var jenis = activity?.let { PrefSaveQuestion10(it).getData()?.toInt() }
        var kompotensi = activity?.let { PrefSaveQuestion11(it).getData()?.toInt() }
        var perilaku = activity?.let { PrefSaveQuestion12(it).getData()?.toInt() }
        var penanganan = activity?.let { PrefSaveQuestion13(it).getData()?.toInt() }
        var sarpras = activity?.let { PrefSaveQuestion14(it).getData()?.toInt() }

        //uncoment, jgn lupa ganti base url di class Service Generator
       // isi username dan pass
        showProgressDialog("Sedang Submit Data   . . .")
        val api = ServiceGenerator.createService(ApiInterface::class.java, Constant().USERNAME, Constant().PASS)
        api.postData(
            sator!!,
            layanan!!,
            usia!!,
            gender!!,
            pendidikan!!,
            perkerjaan!!,
            persyaratan!!,
            smp!!,
            waktu!!,
            jenis!!,
            kompotensi!!,
            perilaku!!,
            penanganan!!,
            sarpras!!

        ).enqueue(object : retrofit2.Callback<ResponseSumbitSurvey> {

            override fun onFailure(call: Call<ResponseSumbitSurvey>, t: Throwable) {
                Log.e("tag", "gagal ${t.message}")
                dismissProgressDialog()
                showErrorMessage("Submit Data Error, Periksa Internet")
            }

            override fun onResponse(call: Call<ResponseSumbitSurvey>, response: Response<ResponseSumbitSurvey>) {
                Log.e("tag", "sukses ${response.isSuccessful}")

                if (response.isSuccessful) {
                    dismissProgressDialog()


                    val builder = AlertDialog.Builder(activity!!)
                    builder.setTitle("Info ")
                    builder.setMessage("Data anda berhasil di kirim, terima kasih telah memberi jawaban survey")
                    builder.setCancelable(false)
                    //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

                    builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                        activity?.let { PrefSaveQuestionSatu(it).setData("") }
                        activity?.let { PrefSaveQuestionDua(it).setData("") }
                        activity?.let { PrefSaveQuestionTiga(it).setData("") }
                        activity?.let { PrefSaveQuestionEmpat(it).setData("") }
                        activity?.let { PrefSaveQuestionLima(it).setData("") }
                        activity?.let { PrefSaveQuestion6(it).setData("") }
                        activity?.let { PrefSaveQuestion7(it).setData("") }
                        activity?.let { PrefSaveQuestion8(it).setData("") }
                        activity?.let { PrefSaveQuestion9(it).setData("") }
                        activity?.let { PrefSaveQuestion10(it).setData("") }
                        activity?.let { PrefSaveQuestion11(it).setData("") }
                        activity?.let { PrefSaveQuestion12(it).setData("") }
                        activity?.let { PrefSaveQuestion13(it).setData("") }
                        activity?.let { PrefSaveQuestion14(it).setData("") }

                        intentTo(MainActivity::class.java)
                        exitProcess(0)

                    }




                    builder.show()





                }
            }
        })



//        showProgressDialog("Submit Data ")
//        ApiConfig().instance().postData(
//            sator!!,
//            layanan!!,
//            usia!!,
//            gender!!,
//            pendidikan!!,
//            perkerjaan!!,
//            persyaratan!!,
//            smp!!,
//            waktu!!,
//            jenis!!,
//            kompotensi!!,
//            perilaku!!,
//            penanganan!!,
//            sarpras!!
//
//        ).enqueue(object : retrofit2.Callback<ResponseSumbitSurvey> {
//
//            override fun onFailure(call: Call<ResponseSumbitSurvey>, t: Throwable) {
//                Log.e("tag", "gagal ${t.message}")
//                dismissProgressDialog()
//                showErrorMessage("Submit Data Error, Periksa Internet")
//            }
//
//            override fun onResponse(call: Call<ResponseSumbitSurvey>, response: Response<ResponseSumbitSurvey>) {
//                Log.e("tag", "sukses ${response.isSuccessful}")
//
//                if (response.isSuccessful) {
//                    dismissProgressDialog()
//
//
//                    val builder = AlertDialog.Builder(activity!!)
//                    builder.setTitle("Info ")
//                    builder.setMessage("Data anda berhasil di kirim, terima kasih telah memberi jawaban survey")
//                    builder.setCancelable(false)
//                    //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))
//
//                    builder.setPositiveButton(android.R.string.yes) { dialog, which ->
//                        activity?.let { PrefSaveQuestionSatu(it).setData("") }
//                        activity?.let { PrefSaveQuestionDua(it).setData("") }
//                        activity?.let { PrefSaveQuestionTiga(it).setData("") }
//                        activity?.let { PrefSaveQuestionEmpat(it).setData("") }
//                        activity?.let { PrefSaveQuestionLima(it).setData("") }
//                        activity?.let { PrefSaveQuestion6(it).setData("") }
//                        activity?.let { PrefSaveQuestion7(it).setData("") }
//                        activity?.let { PrefSaveQuestion8(it).setData("") }
//                        activity?.let { PrefSaveQuestion9(it).setData("") }
//                        activity?.let { PrefSaveQuestion10(it).setData("") }
//                        activity?.let { PrefSaveQuestion11(it).setData("") }
//                        activity?.let { PrefSaveQuestion12(it).setData("") }
//                        activity?.let { PrefSaveQuestion13(it).setData("") }
//                        activity?.let { PrefSaveQuestion14(it).setData("") }
//
//                        intentTo(MainActivity::class.java)
//                    }
//
//
//
//
//                    builder.show()
//
//
//
//
//
//                }
//            }
//        })

    }

    private fun initData() {
        tvNo?.text = "14"
        tvQuestion?.text = "Kualitas sarana dan prasarana dalam pelayanan yang diberikan?"

        var data: List<ModelSatuanKerja> =
            listOf(
                ModelSatuanKerja(
                    "1",
                    "Sangat Tidak berkualitas"
                ),
                ModelSatuanKerja(
                    "2",
                    "Tidak Berkualitas"
                ),
                ModelSatuanKerja(
                    "3",
                    "Berkualitas"
                ),
                ModelSatuanKerja(
                    "4",
                    "Sangat Berkualitas"
                )
            )

        val adapter = Jabawan14Adapter(activity, data)
        rvDataQuestion?.adapter = adapter
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickListener(object : Jabawan14Adapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                activity?.let { PrefSaveQuestion14(it).setData(data.get(position).id.toString()) }
                initData()
                tvNext?.visibility = View.VISIBLE
                tvNext?.text = "Submit Data"

            }
        })


    }

    public fun openFragment(fragment: Fragment) {
        if (savedInstanceState == null) {
            childFragmentManager
                .beginTransaction()
                .replace(R.id.root_fragment, fragment, fragment.javaClass.simpleName)
                .commit()
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
    }

    override fun onDetach() {
        super.onDetach()
        //listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

}
