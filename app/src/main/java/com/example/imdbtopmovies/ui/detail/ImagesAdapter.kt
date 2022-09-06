package com.example.imdbtopmovies.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.imdbtopmovies.databinding.ItemDetailImageBinding
import javax.inject.Inject

class ImagesAdapter @Inject constructor() : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    lateinit var binding: ItemDetailImageBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemDetailImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = differ.currentList.size


    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        fun bind(url:String) {
            binding.apply {
                movieImage.load(url){
                    crossfade(true)
                    crossfade(500)
                }
            }
        }

    }


    private val differCallBack =
        object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(
                oldItem: String,
                newItem: String
            ): Boolean {
                return (oldItem == newItem)
            }

            override fun areContentsTheSame(
                oldItem: String,
                newItem: String
            ): Boolean {
                return (oldItem == newItem)
            }
        }


    val differ = AsyncListDiffer(this, differCallBack)

}