package dev.khalil.peekture.view.holder

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.khalil.peekture.R
import dev.khalil.peekture.databinding.PhotoListItemBinding
import dev.khalil.peekture.model.PhotosUi


class PhotosViewHolder(val view: PhotoListItemBinding) : RecyclerView.ViewHolder(view.root) {

    fun bind(photo: PhotosUi) {
        Picasso.get()
            .load(photo.url)
            .placeholder(R.drawable.placeholder)
            .into(view.photoImageView)
    }
}
