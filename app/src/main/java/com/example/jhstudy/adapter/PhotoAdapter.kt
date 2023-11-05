package com.example.jhstudy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jhstudy.databinding.ItemPhotoBinding
import com.example.jhstudy.models.PhotoResponse

class PhotoAdapter(private val itemClickedListener:(String)->Unit) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {
    var photos: List<PhotoResponse> = emptyList()

    inner class ViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: PhotoResponse) {
            binding.root.setOnClickListener {
                itemClickedListener(photo.urls.regular)
            }
            Glide.with(binding.root)
                .load(photo.urls?.regular)
                .into(binding.image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photos[position])
    }


}