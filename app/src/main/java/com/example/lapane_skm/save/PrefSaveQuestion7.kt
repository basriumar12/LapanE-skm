package com.example.lapane_skm.save

import android.content.Context
import android.content.SharedPreferences

class PrefSaveQuestion7 (private val context: Context){

    val sp: SharedPreferences = context.getSharedPreferences("image",0)
    val editor: SharedPreferences.Editor = sp.edit()

    fun setData(image:String?){
        editor.putString("tujuh",image)
        editor.commit()
    }

    fun getData() : String?{

        return sp.getString("tujuh",null)

    }
}