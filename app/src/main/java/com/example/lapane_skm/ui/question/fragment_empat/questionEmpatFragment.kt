package com.example.lapane_skm.ui.question.fragment_empat

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.lapane_skm.R
import com.example.lapane_skm.save.PrefSaveQuestionEmpat
import com.example.lapane_skm.save.PrefSaveQuestionTiga
import com.example.lapane_skm.ui.question.BaseFragment
import com.example.lapane_skm.ui.question.questionsatu.ModelSatuanKerja
import com.example.lapane_skm.ui.question.fragment_dua.QuestionDuaFragment
import com.example.lapane_skm.ui.question.fragment_lima.QuestionLimaFragment
import com.example.lapane_skm.ui.question.fragment_tiga.JabawanTigaAdapter
import com.example.lapane_skm.ui.question.fragment_tiga.QuestionTigaFragment
import kotlinx.android.synthetic.main.activity_question_satu.*
import kotlinx.android.synthetic.main.fragment_question_dua.view.*

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
class QuestionEmpatFragment : BaseFragment() {
    val isDataQuestion : String? = null
    private var listener: OnFragmentInteractionListener? = null
    var tvNo : TextView? =null
    var tvQuestion : TextView? =null
    var tvNext : TextView? =null
    var tvprev : TextView? =null
    var rvDataQuestion : RecyclerView? = null
    private var savedInstanceState: Bundle? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_question_dua, container, false)
        tvNo = view.tv_no_question
        tvQuestion = view.tv_question
        tvNext = view.tv_next
        tvprev = view.tv_prev
        rvDataQuestion = view.rv_data_jawaban
        rvDataQuestion?.layoutManager = GridLayoutManager(activity,2)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvNo?.text ="4"
        tvQuestion?.text ="Jenis Kelamin *"
        initData()


        val isDataQuestion = activity?.let { PrefSaveQuestionEmpat(it).getData().toString() }.toString()
        Log.e("data ","data question tiga $isDataQuestion")

        val validasiData = isDataQuestion.toString()
        if (!validasiData.equals("null")){
            tvNext?.visibility = View.VISIBLE
        } else{
            tvNext?.visibility = View.GONE
        }
        tvNext?.setOnClickListener {
            openFragment(QuestionLimaFragment())
        }

        tv_prev.setOnClickListener {
            openFragment(QuestionTigaFragment())
        }




    }

    private fun initData() {
        var data: List<ModelSatuanKerja> =
            listOf(
                ModelSatuanKerja(
                    "1",
                    "laki - Laki"
                ),
                ModelSatuanKerja(
                    "2",
                    "Perempuan"
                )
            )

        val adapter = JabawanEmpatAdapter(activity,data)
        rvDataQuestion?.adapter = adapter
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickListener(object : JabawanEmpatAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                activity?.let { PrefSaveQuestionEmpat(it).setData(data.get(position).id) }
                openFragment(QuestionLimaFragment())


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
