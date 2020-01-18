package dev.khalil.peekture.view.holder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.khalil.peekture.databinding.PhotoListItemBinding

class PhotosViewHolder(val view: PhotoListItemBinding) : RecyclerView.ViewHolder(view.root) {

    fun bind(photoUrl: String) {
        Glide.with(view.root.context)
            .load(photoUrl)
            .into(view.photoImageView)
    }
}
