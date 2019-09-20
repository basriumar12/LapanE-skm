package com.example.lapane_skm.network

import com.example.lapane_skm.model.ResponseSumbitSurvey
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


// class ini base endpoint ketika menggunakan library retrofit
interface ApiInterface {


    /// post method yang digunakan ketika rest api menggunakan post dengan form
    @POST("survey")
    @FormUrlEncoded
    fun postData(
        @Field("sator")sator : Int,
        @Field("layanan") layanan : Int,
        @Field("usia") usia : String,
        @Field("gender") gender : String,
        @Field("pendidikan") pendidikan : String,
        @Field("pekerjaan") pekerjaan : String,
        @Field("persyaratan") persyaratan : Int,
        @Field("smp") smp : Int,
        @Field("waktu") waktu : Int,
        @Field("jenis") jenis : Int,
        @Field("kompetensi") kompotensi : Int,
        @Field("perilaku") perilaku : Int,
        @Field("penanganan") penanganan : Int,
        @Field("sarpras") sarpras : Int

    ): Call<ResponseSumbitSurvey>
}