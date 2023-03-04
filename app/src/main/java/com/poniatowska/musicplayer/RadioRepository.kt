package com.poniatowska.musicplayer

import android.content.Context
import java.io.IOException
import java.io.InputStream

class RadioRepository {
    fun getRadioStringFromFile(context: Context):String{
        return try {
            val inputStream: InputStream = context.assets.open("json_radio_url_list.txt")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
            "err"
        }
    }
}