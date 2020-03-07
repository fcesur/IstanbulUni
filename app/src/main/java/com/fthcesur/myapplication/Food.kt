package com.fthcesur.myapplication

import com.google.gson.annotations.SerializedName

data class Food(
        @SerializedName("tarih") val date: String?,
        @SerializedName("menu") val menu: String?,
        @SerializedName("kalori") val calorie: String?
)