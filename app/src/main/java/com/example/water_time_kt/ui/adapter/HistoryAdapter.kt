package com.example.water_time_kt.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.water_time_kt.R
import com.example.water_time_kt.data.DrinkDay

class HistoryAdapter(
    private val values: List<DrinkDay>
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.list_history_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.time.text = item.date
        holder.volume.text = item.dayResult.toString()
        holder.checkBox.setChecked(item.completed)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val volume: TextView = view.findViewById<View>(R.id.history_item_volume) as TextView
        val time: TextView = view.findViewById<View>(R.id.history_item_time) as TextView
        val checkBox: CheckBox = view.findViewById<View>(R.id.checkBox) as CheckBox
    }

}