package com.example.jhstudy.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.jhstudy.databinding.ActivityDetailBinding

class DetailActivity: AppCompatActivity(){
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getImage()

    }


    private fun getImage(){
        val uri = intent.getStringExtra("uri")
        Log.d("getUri",uri.toString())

        Glide.with(this)
            .load(uri)
            .into(binding.detail)
    }

}