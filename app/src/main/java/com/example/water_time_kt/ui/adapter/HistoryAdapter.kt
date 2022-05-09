package com.example.water_time_kt.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.water_time_kt.R
import com.example.water_time_kt.data.DrinkDay
import kotlinx.android.synthetic.main.list_history_item.view.*

class HistoryAdapter: RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private var values: MutableList<DrinkDay> = mutableListOf()

    fun refresh(list: List<DrinkDay>){
        Log.e("cc",list.toString())
        values.clear()
        values.addAll(list)
        notifyDataSetChanged()
        Log.e("cc",values.toString())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.list_history_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.volume.text = item.dayResult.toString()
        holder.time.text = item.date
        holder.checkBox.isChecked = item.completed
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val volume: TextView = view.history_item_volume
        val time: TextView = view.history_item_time
        val checkBox: CheckBox = view.checkBox
    }

}