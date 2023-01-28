package com.example.m5hw5.hilt.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.m5hw5.databinding.ItemPhotoBinding
import com.example.m5hw5.hilt.data.models.PhotoModel

class PhotoAdapter : ListAdapter<PhotoModel, PhotoAdapter.PhotoViewHolder>(diffUtil) {

    inner class PhotoViewHolder(private val binding: ItemPhotoBinding) : ViewHolder(binding.root) {

        fun onBind(item: PhotoModel) {
            Glide.with(binding.ivImage.context).load(item.url + ".png").into(binding.ivImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<PhotoModel>() {
            override fun areItemsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}