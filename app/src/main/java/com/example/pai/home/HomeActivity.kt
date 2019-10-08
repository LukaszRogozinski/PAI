package com.example.pai.home

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.pai.R
import com.example.pai.adapters.EventCategoryListAdapter
import com.example.pai.databinding.ActivityHomeBinding
import com.squareup.picasso.Picasso

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    private val viewModel: HomeViewModel by lazy {

        ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

//        binding.lifecycleOwner = this

        val adapter = EventCategoryListAdapter()
        binding.eventCetegoryRecyclerView.adapter = adapter

        viewModel.properties.observe(this, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

//        viewModel.properties.observe(this, Observer {
//            response ->
//            run {
//                binding.responseText.text = response.imageUrl
//                val imgUri = response.imageUrl.toUri().buildUpon().scheme("https").build()
//                bindImg(imgUri)
//            }
//        })

    }

    private fun bindImg(imgUri: Uri) {
        Picasso.get()
            .load(imgUri)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
            .into(binding.imageView)
    }

}
