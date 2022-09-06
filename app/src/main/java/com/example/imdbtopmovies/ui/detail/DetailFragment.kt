package com.example.imdbtopmovies.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.imdbtopmovies.R
import com.example.imdbtopmovies.databinding.FragmentDetailBinding
import com.example.imdbtopmovies.db.MovieEntity
import com.example.imdbtopmovies.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    //Binding
    lateinit var binding: FragmentDetailBinding

    //ViewModel
    val viewModel: DetailViewModel by viewModels()

    @Inject
    lateinit var actorsImageAdapter: ImagesAdapter

    @Inject
    lateinit var entity: MovieEntity
    private var movieID = 0
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieID = args.id
        if (movieID > 0)
            viewModel.getMovieDetail(movieID)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            viewModel.detail.observe(viewLifecycleOwner) { movie ->
//init Views
                posterBigImage.load(movie.poster)
                posterNormalImage.load(movie.poster) {
                    crossfade(true)
                    crossfade(800)
                }
                movieTitleText.text = movie.title
                movieRating.text = movie.imdbRating
                movieReleaseDate.text = movie.released
                movieTime.text = movie.runtime
                summeryText.text = movie.plot
                actorsText.text = movie.actors
//Init Recycler
                actorsImageAdapter.differ.submitList(movie.images)
                actorsRecycler.apply {
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    adapter = actorsImageAdapter
                }


//          Favorite Click
                favoriteImage.setOnClickListener {
                    entity.id = movie.id!!
                    entity.country = movie.country!!
                    entity.poster = movie.poster!!
                    entity.imdbRating = movie.imdbRating!!
                    entity.title = movie.title!!
                    entity.year = movie.year!!
                    viewModel.favoriteMovie(movieID, entity)
                }
            }

//                favorite
            viewModel.isFavorite.observe(viewLifecycleOwner) {
                if (it) {
                    favoriteImage.setColorFilter(
                        ContextCompat.getColor(requireContext(), R.color.scarlet)
                    )
                } else {
                    favoriteImage.setColorFilter(
                        ContextCompat.getColor(requireContext(), R.color.philippineSilver)
                    )
                }
            }

//            init favorite
            lifecycleScope.launchWhenCreated {
                if (viewModel.exists(movieID)) {
                    favoriteImage.setColorFilter(
                        ContextCompat.getColor(requireContext(), R.color.scarlet)
                    )
                } else {
                    favoriteImage.setColorFilter(
                        ContextCompat.getColor(requireContext(), R.color.philippineSilver)
                    )
                }
            }
//      back Click
            backImage.setOnClickListener {
                findNavController().navigateUp()
            }

//            loading
            viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
                loading.visibility = if (isLoading) View.VISIBLE else View.GONE
                movieLayout.visibility = if (isLoading) View.GONE else View.VISIBLE
            }

        }


    }
}
