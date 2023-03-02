package com.poniatowska.musicplayer

import android.content.Context
import java.io.IOException
import java.io.InputStream

class RadioRepository {
    fun getRadioStringFromFile(context: Context):String{
        try {
            val inputStream: InputStream = context.assets.open("json_radio_url_list.txt")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            return String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
            return "err"
        }
    }
}