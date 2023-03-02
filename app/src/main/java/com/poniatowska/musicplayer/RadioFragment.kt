package com.poniatowska.musicplayer

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RadioFragment : Fragment() {

    companion object {
        fun newInstance() = RadioFragment()
    }

    private lateinit var viewModel: RadioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_radio, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[RadioViewModel::class.java]
        viewModel.getRadioList(requireContext())
        viewModel.radioMutableLiveData.observe(requireActivity()){
            Log.e("url",it.toString())
            //todo recycler view
        }
    }

}