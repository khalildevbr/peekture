package dev.khalil.peekture.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dev.khalil.peekture.R
import dev.khalil.peekture.databinding.PhotoListItemBinding
import dev.khalil.peekture.model.PhotosUi
import dev.khalil.peekture.view.holder.PhotosViewHolder

class PhotosListAdapter : RecyclerView.Adapter<PhotosViewHolder>() {

    private var photos: List<PhotosUi> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val view = DataBindingUtil.inflate<PhotoListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.photo_list_item,
            parent,
            false
        )

        return PhotosViewHolder(view)
    }

    override fun getItemCount() = photos.size

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    fun addPhotos(photos: List<PhotosUi>) {
        this.photos = photos
        notifyDataSetChanged()
    }
}