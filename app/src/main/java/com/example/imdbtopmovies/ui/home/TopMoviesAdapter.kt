package com.example.imdbtopmovies.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.imdbtopmovies.databinding.ItemTopMovieBinding
import com.example.imdbtopmovies.models.home.TopMoviesResponse
import javax.inject.Inject

class TopMoviesAdapter @Inject constructor() : RecyclerView.Adapter<TopMoviesAdapter.ViewHolder>() {

    lateinit var binding: ItemTopMovieBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemTopMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = if (differ.currentList.size > 5) 5 else differ.currentList.size


    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: TopMoviesResponse.Data) {
            binding.apply {
                movieName.text = data.title
                movieInfo.text = "${data.imdbRating} | ${data.country} | ${data.year} "
                movieImage.load(data.poster) {
                    crossfade(true)
                    crossfade(1000)
                }
            }
        }

    }


    private val differCallBack = object : DiffUtil.ItemCallback<TopMoviesResponse.Data>() {
        override fun areItemsTheSame(
            oldItem: TopMoviesResponse.Data,
            newItem: TopMoviesResponse.Data
        ): Boolean {
            return (oldItem.id == newItem.id)
        }

        override fun areContentsTheSame(
            oldItem: TopMoviesResponse.Data,
            newItem: TopMoviesResponse.Data
        ): Boolean {
            return (oldItem == newItem)
        }
    }


    val differ = AsyncListDiffer(this, differCallBack)

}