package com.poniatowska.musicplayer

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ExpandableListView.OnChildClickListener
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.poniatowska.musicplayer.databinding.SingleRowRadioBinding

class RadioListAdapter(private val context: Context, private var data: ArrayList<Radio>, private val clickListener: (Radio) -> Unit): RecyclerView.Adapter<RadioListAdapter.ViewHolder>() {

    fun updateData(data: ArrayList<Radio>){
        this.data = data
    }

    inner class ViewHolder (val binding: SingleRowRadioBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SingleRowRadioBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        holder.binding.nameTextView.text = item.name
        holder.binding.onOffBtn.setOnClickListener {
            clickListener(item)
        }

        if (item.onOff) holder.binding.onOffBtn.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_audio_pause_circle))
        else holder.binding.onOffBtn.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_audio_play_circle))

    }
}