package com.poniatowska.musicplayer

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GetRadioList {
    fun getRadioList(context: Context): ArrayList<Radio>{
        val radioJsonAsString = RadioRepository().getRadioStringFromFile(context)
        val sType = object : TypeToken<ArrayList<Radio>>() { }.type
        return try {
            Gson().fromJson(radioJsonAsString, sType)
        }catch (e: Exception){
            e.printStackTrace()
            ArrayList()
        }

    }
}