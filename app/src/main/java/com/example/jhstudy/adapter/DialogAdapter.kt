package com.example.jhstudy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jhstudy.databinding.ItemDialogPhotoBinding

import com.example.jhstudy.models.PhotoResponse


class DialogAdapter(private val itemClickedListener:(PhotoResponse)->Unit) : RecyclerView.Adapter<DialogAdapter.ViewHolder>() {
    var photos: List<PhotoResponse> = emptyList()

    inner class ViewHolder(private val binding: ItemDialogPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: PhotoResponse) {
            binding.root.setOnClickListener {
                itemClickedListener(photo)
            }
            Glide.with(binding.root)
                .load(photo.urls?.regular)
                .into(binding.image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemDialogPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photos[position])
    }


}