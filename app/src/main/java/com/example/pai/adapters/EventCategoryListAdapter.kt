package com.example.pai.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.databinding.adapters.LinearLayoutBindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pai.R
import com.example.pai.databinding.ActivityHomeBinding
import com.example.pai.network.EventCategory

class EventCategoryListAdapter : RecyclerView.Adapter<EventCategoryItemViewHolder>() {

    var data = listOf<EventCategory>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventCategoryItemViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView
        return EventCategoryItemViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: EventCategoryItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.imageUrl
    }

}

class EventCategoryItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)