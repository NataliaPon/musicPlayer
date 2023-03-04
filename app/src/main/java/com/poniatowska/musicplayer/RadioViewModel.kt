package com.poniatowska.musicplayer

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RadioViewModel : ViewModel() {
    val radioMutableLiveData :MutableLiveData<ArrayList<Radio>> = MutableLiveData()
    private lateinit var mediaPlayerService: MediaPlayerService

    fun getRadioList(context: Context){
        viewModelScope.launch { radioMutableLiveData.postValue(GetRadioList().getRadioList(context)) }
    }

    fun playOrPauseRadio(radio: Radio){
        if (radio.onOff) playRadio(radio)
        else pauseRadio()
    }

    private fun playRadio(radio: Radio){
        viewModelScope.launch(Dispatchers.IO){
            mediaPlayerService = MediaPlayerService()
            mediaPlayerService.runRadioByUrl(radio.url)
        }
    }

    private fun pauseRadio(){
        if (this::mediaPlayerService.isInitialized)
            mediaPlayerService.pauseRadio()
    }

}