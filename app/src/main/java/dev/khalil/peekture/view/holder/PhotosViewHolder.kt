package dev.khalil.peekture.view.holder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.khalil.peekture.databinding.PhotoListItemBinding
import dev.khalil.peekture.model.PhotosUi

class PhotosViewHolder(val view: PhotoListItemBinding) : RecyclerView.ViewHolder(view.root) {

    fun bind(photo: PhotosUi) {
        Glide.with(view.root.context)
            .load(photo.url)
            .into(view.photoImageView)
    }
}
