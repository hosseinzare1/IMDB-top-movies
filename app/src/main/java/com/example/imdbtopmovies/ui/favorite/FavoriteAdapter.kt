package com.example.imdbtopmovies.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.imdbtopmovies.databinding.ItemLastMoviesBinding
import com.example.imdbtopmovies.db.MovieEntity
import javax.inject.Inject

class FavoriteAdapter @Inject constructor() :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    lateinit var binding: ItemLastMoviesBinding

    private var moviesList = emptyList<MovieEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemLastMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(moviesList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = moviesList.size


    fun setData(data: List<MovieEntity>) {

        val diffUtilCallBack = DiffCallBack(moviesList, data)
        val differ = DiffUtil.calculateDiff(diffUtilCallBack)
        moviesList = data
        differ.dispatchUpdatesTo(this)


    }

    class DiffCallBack(
        private val oldItems: List<MovieEntity>,
        private val newItems: List<MovieEntity>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldItems.size
        }

        override fun getNewListSize(): Int {
            return newItems.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition] === newItems[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition] === newItems[newItemPosition]
        }
    }

    private var onClickListener: ((MovieEntity) -> Unit)? = null

    fun setOnClickListener(onClickListener: ((MovieEntity) -> Unit)) {
        this.onClickListener = onClickListener
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: MovieEntity) {
            binding.apply {
                movieTitle.text = data.title
                movieCountry.text = data.country
                movieYear.text = data.year
                movieRating.text = data.imdbRating
//                movieName.text = data.title
//                movieInfo.text = "${data.imdbRating} | ${data.country} | ${data.year} "
                moviePosterImage.load(data.poster) {
                    crossfade(true)
                    crossfade(500)
                }

//                Click Listener
                root.setOnClickListener {
                    onClickListener?.let {
                        it(data)
                    }

                }
            }


        }

    }


}