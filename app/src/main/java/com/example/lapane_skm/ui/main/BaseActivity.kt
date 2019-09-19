package com.example.lapane_skm.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.example.lapane_skm.R
import com.valdesekamdem.library.mdtoast.MDToast
import kotlin.system.exitProcess

open class BaseActivity : AppCompatActivity() {


    lateinit var loading: MaterialDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val materialDialog = MaterialDialog.Builder(this)
        materialDialog.progress(true, 0)
        materialDialog.cancelable(false)
        loading = materialDialog.build()

    }

   fun intentTo(target: Class<*>) {
        startActivity(Intent(this, target))
    }

fun showErrorMessage(message: String?) {
        MDToast.makeText(this, message, MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show()
    }

   fun showSuccessMessage(message: String?) {
        MDToast.makeText(this, message, MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show()
    }

 fun showInfoMessage(message: String?) {
        MDToast.makeText(this, message, MDToast.LENGTH_SHORT, MDToast.TYPE_INFO).show()
    }

   fun showLongErrorMessage(message: String?) {
        MDToast.makeText(this, message, MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show()
    }

 fun showLongSuccessMessage(message: String?) {
        MDToast.makeText(this, message, MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS).show()
    }

  fun showLongInfoMessage(message: String?) {
        MDToast.makeText(this, message, MDToast.LENGTH_LONG, MDToast.TYPE_INFO).show()
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

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this@BaseActivity)
        builder.setTitle("Info ")
        builder.setMessage("apakah anda ingin membatalkan pengisian pertanyaan ?")
        //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            finish()
            exitProcess(0)

        }
        builder.setNegativeButton(android.R.string.no) { dialog, which ->

        }

        builder.show()
    }



}