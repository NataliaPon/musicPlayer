package com.poniatowska.musicplayer

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RadioViewModel : ViewModel() {
    val radioMutableLiveData :MutableLiveData<ArrayList<Radio>> = MutableLiveData()

    fun getRadioList(context: Context){
        viewModelScope.launch { radioMutableLiveData.postValue(GetRadioList().getRadioList(context)) }
    }


}