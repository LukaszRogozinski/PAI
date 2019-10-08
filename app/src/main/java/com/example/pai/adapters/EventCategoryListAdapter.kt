package com.example.pai.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.pai.R
import com.example.pai.network.EventCategory
import com.squareup.picasso.Picasso

class EventCategoryListAdapter : RecyclerView.Adapter<EventCategoryListAdapter.ViewHolder>() {

    var data = listOf<EventCategory>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.event_category_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.name.text = item.name
        bindImg(item.imageUrl, holder.image)
    }

    private fun bindImg(imgString: String, imageView: ImageView) {
        val imgUri = imgString.toUri().buildUpon().scheme("https").build()
        Picasso.get()
            .load(imgUri)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
            .into(imageView)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.eventCategoryName)
        val image: ImageView = itemView.findViewById(R.id.eventCategoryImage)
    }

}
