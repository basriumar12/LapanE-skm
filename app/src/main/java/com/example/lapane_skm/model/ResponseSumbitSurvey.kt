package com.example.lapane_skm.model

import com.google.gson.annotations.SerializedName

data class ResponseSumbitSurvey(

	@field:SerializedName("usia")
	val usia: String? = null,

	@field:SerializedName("persyaratan")
	val persyaratan: String? = null,

	@field:SerializedName("id_isi")
	val idIsi: String? = null,

	@field:SerializedName("tgl_survey")
	val tglSurvey: String? = null,

	@field:SerializedName("layanan")
	val layanan: String? = null,

	@field:SerializedName("id_survey")
	val idSurvey: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("pendidikan")
	val pendidikan: String? = null,

	@field:SerializedName("id_sator")
	val idSator: String? = null,

	@field:SerializedName("perilaku")
	val perilaku: String? = null,

	@field:SerializedName("kompetensi")
	val kompetensi: String? = null,

	@field:SerializedName("pekerjaan")
	val pekerjaan: String? = null,

	@field:SerializedName("sarpras")
	val sarpras: String? = null,

	@field:SerializedName("penanganan")
	val penanganan: String? = null,

	@field:SerializedName("smp")
	val smp: String? = null,

	@field:SerializedName("waktu")
	val waktu: String? = null,

	@field:SerializedName("jenis")
	val jenis: String? = null
)