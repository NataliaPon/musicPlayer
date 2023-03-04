package com.poniatowska.musicplayer

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.poniatowska.musicplayer.databinding.FragmentRadioBinding

class RadioFragment : Fragment() {

    private lateinit var radioListAdapter: RadioListAdapter

    private var _binding: FragmentRadioBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = RadioFragment()
    }

    private lateinit var viewModel: RadioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRadioBinding.inflate(inflater, container, false)
        val view = binding.root

        radioListAdapter = RadioListAdapter(requireContext(), ArrayList()) {
            Log.e("clicked item", it.toString())
            viewModel.radioMutableLiveData.value!!.forEach { radio ->
                //                //todo jeśli radio było odpalone (miało true) to trzeba je wyłączyć
                if(radio != it) //radio.onOff = false
                    if (radio.onOff) {
                        it.onOff = !it.onOff
                        viewModel.playOrPauseRadio(radio)
                    }
            }
            it.onOff = !it.onOff
            radioListAdapter.notifyDataSetChanged()
            viewModel.playOrPauseRadio(it)
        }

        binding.radioListRecycler.layoutManager = LinearLayoutManager(activity)
        binding.radioListRecycler.adapter = radioListAdapter

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[RadioViewModel::class.java]
        viewModel.getRadioList(requireContext())

        viewModel.radioMutableLiveData.observe(requireActivity()){
            radioListAdapter.updateData(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}