package com.codinginflow.imagesearchapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.codinginflow.imagesearchapp.R
import com.codinginflow.imagesearchapp.databinding.ActivityMainBinding
import com.codinginflow.imagesearchapp.databinding.ItemPhotoBinding
import com.codinginflow.imagesearchapp.models.UnsplashPhoto

class UnsplashPhotoAdapter: PagingDataAdapter<UnsplashPhoto, UnsplashPhotoAdapter.PhotoViewHolder>(
    PHOTO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding: ItemPhotoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_photo,
            parent,
            false
        )

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)

        if(currentItem != null) {
            holder.bind(currentItem)
        }
    }

    companion object {
        private val PHOTO_COMPARATOR = object: DiffUtil.ItemCallback<UnsplashPhoto>() {
            override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto) =
                oldItem == newItem
        }
    }

    class PhotoViewHolder(private val binding: ItemPhotoBinding):
        RecyclerView.ViewHolder(binding.root){

            fun bind(photo: UnsplashPhoto) {
                binding.apply {
                    Glide.with(itemView)
                        .load(photo.urls.regular)
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .error(R.drawable.ic_error)
                        .into(imageView)

                    tvUsername.text = photo.user.userName
                }
            }
    }
}