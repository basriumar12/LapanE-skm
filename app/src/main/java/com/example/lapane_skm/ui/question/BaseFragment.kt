package com.example.lapane_skm.ui.question

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import com.example.lapane_skm.R
import com.valdesekamdem.library.mdtoast.MDToast

open class BaseFragment: Fragment() {


    lateinit var loading: MaterialDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val materialDialog = MaterialDialog.Builder(activity!!)
        materialDialog.progress(true, 0)
        materialDialog.cancelable(false)
        loading = materialDialog.build()

    }

     fun intentTo(target:Class<*>){
        startActivity(Intent(activity,target))
    }

    fun showErrorMessage(message: String?) {
        MDToast.makeText(activity, message, MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show()
    }

    fun showSuccessMessage(message: String?) {
        MDToast.makeText(activity, message, MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show()
    }

    fun showInfoMessage(message: String?) {
        MDToast.makeText(activity, message, MDToast.LENGTH_SHORT, MDToast.TYPE_INFO).show()
    }

    fun showLongErrorMessage(message: String?) {
        MDToast.makeText(activity, message, MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show()
    }

    fun showLongSuccessMessage(message: String?) {
        MDToast.makeText(activity, message, MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS).show()
    }

    fun showLongInfoMessage(message: String?) {
        MDToast.makeText(activity, message, MDToast.LENGTH_LONG, MDToast.TYPE_INFO).show()
    }

    fun showProgressDialog() {
        loading.setContent(R.string.progress_dialog)
        loading.show()
    }
    fun showProgressDialog(message: String?) {
        loading.setContent(message)
        loading.show()
    }

    fun updateMessageDialog(message: String?) {
        loading.setContent(message)
    }

    fun dismissProgressDialog() {
        if (loading.isShowing)
            loading.dismiss()
    }

}