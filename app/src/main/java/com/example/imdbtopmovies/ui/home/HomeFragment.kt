package com.example.imdbtopmovies.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.imdbtopmovies.databinding.FragmentHomeBinding
import com.example.imdbtopmovies.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {


    //Binding
    lateinit var binding: FragmentHomeBinding

    private val snapHelper: PagerSnapHelper by lazy { PagerSnapHelper() }

    @Inject
    lateinit var topMoviesAdapter: TopMoviesAdapter

    @Inject
    lateinit var genresAdapter: GenresAdapter

    @Inject
    lateinit var lastMoviesAdapter: LastMoviesAdapter

    val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //request to server
        viewModel.getTopMovies(3)
        viewModel.getGenres()
        viewModel.getLastMovies()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.apply {
            //Get top movies
            viewModel.topMoviesList.observe(viewLifecycleOwner) {
                topMoviesAdapter.differ.submitList(it.data)
            }

            //Init recycler
            homeTopMoviesRecycler.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = topMoviesAdapter
            }

            //Init indicator
            snapHelper.attachToRecyclerView(homeTopMoviesRecycler)
            topMovieIndicator.attachToRecyclerView(homeTopMoviesRecycler, snapHelper)
            topMoviesAdapter.registerAdapterDataObserver(topMovieIndicator.adapterDataObserver)


            //Get Genres
            viewModel.genresList.observe(viewLifecycleOwner) {
                genresAdapter.differ.submitList(it)
            }

            //Init recycler
            genresRecycler.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = genresAdapter
            }

            //Get last movies
            viewModel.lastMoviesList.observe(viewLifecycleOwner) {
                lastMoviesAdapter.setData(it.data)
            }

            //Init recycler
            lastMoviesRecycler.apply {
                layoutManager =
                    LinearLayoutManager(requireContext())
                adapter = lastMoviesAdapter
            }

            //Click
            lastMoviesAdapter.setOnClickListener {
                val direction = HomeFragmentDirections.actionToDetailFragment(it.id!!)
                findNavController().navigate(direction)
            }

            //loading
            viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
                loading.visibility = if (isLoading) View.VISIBLE else View.GONE
                homeScrollView.visibility = if (isLoading) View.GONE else View.VISIBLE
            }

        }

    }

}