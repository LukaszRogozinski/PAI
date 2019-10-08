package com.example.pai.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pai.R
import com.example.pai.network.EventCategory
import com.squareup.picasso.Picasso

class EventCategoryListAdapter : ListAdapter<EventCategory, EventCategoryListAdapter.ViewHolder>(EventCategoryDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.eventCategoryName)
        val image: ImageView = itemView.findViewById(R.id.eventCategoryImage)

        fun bind(item: EventCategory) {
            name.text = item.name
            bindImg(item.imageUrl, image)
        }

        companion object {
             fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.event_category_item, parent, false)
                return ViewHolder(view)
            }
        }

        private fun bindImg(imgString: String, imageView: ImageView) {
            val imgUri = imgString.toUri().buildUpon().scheme("https").build()
            Picasso.get()
                .load(imgUri)
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
                .into(imageView)
        }
    }
}

class EventCategoryDiffCallback : DiffUtil.ItemCallback<EventCategory>() {
    override fun areItemsTheSame(oldItem: EventCategory, newItem: EventCategory): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: EventCategory, newItem: EventCategory): Boolean {
        return oldItem == newItem
    }

}
