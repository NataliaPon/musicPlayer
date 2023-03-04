package com.poniatowska.musicplayer

import android.media.AudioAttributes
import android.media.MediaPlayer

class MediaPlayerService {
    private lateinit var mediaPlayer: MediaPlayer

    suspend fun runRadioByUrl(url: String){
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(url)
            prepare() // might take long! (for buffering, etc)
            start()
        }
    }

    fun pauseRadio(){
        mediaPlayer.pause()
        mediaPlayer.release()
    }
}