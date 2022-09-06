package com.example.imdbtopmovies.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbtopmovies.databinding.ItemGenresBinding
import com.example.imdbtopmovies.models.home.GenresResponse
import javax.inject.Inject

class GenresAdapter @Inject constructor() : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    lateinit var binding: ItemGenresBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemGenresBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = differ.currentList.size


    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: GenresResponse.GenresResponseItem) {
            binding.apply {
                genreTitle.text = data.name
            }
        }

    }


    private val differCallBack =
        object : DiffUtil.ItemCallback<GenresResponse.GenresResponseItem>() {
            override fun areItemsTheSame(
                oldItem: GenresResponse.GenresResponseItem,
                newItem: GenresResponse.GenresResponseItem
            ): Boolean {
                return (oldItem.id == newItem.id)
            }

            override fun areContentsTheSame(
                oldItem: GenresResponse.GenresResponseItem,
                newItem: GenresResponse.GenresResponseItem
            ): Boolean {
                return (oldItem == newItem)
            }
        }


    val differ = AsyncListDiffer(this, differCallBack)

}