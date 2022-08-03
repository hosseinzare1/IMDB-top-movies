package com.example.imdbtopmovies.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdbtopmovies.R
import com.example.imdbtopmovies.databinding.FragmentSearchBinding
import com.example.imdbtopmovies.repository.SearchRepository
import com.example.imdbtopmovies.ui.home.LastMoviesAdapter
import com.example.imdbtopmovies.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {


    //Binging
    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var repository: SearchRepository

    @Inject
    lateinit var moviesAdapter: LastMoviesAdapter

    val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Init Views
        binding.apply {
            //Call Search
            searchEdt.addTextChangedListener {
                val text = it.toString()
                if (text.isNotEmpty()) viewModel.searchMovie(text)
            }

//            Get Movies List
            viewModel.moviesList.observe(viewLifecycleOwner) { movies ->
                moviesAdapter.setData(movies.data)
//                Init Recycler
                moviesList.apply {
                    adapter = moviesAdapter
                    layoutManager = LinearLayoutManager(requireContext())
                }

//              Loading
                viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
                    loading.visibility = if (isLoading) View.VISIBLE else View.GONE
                    moviesList.visibility = if (isLoading) View.GONE else View.VISIBLE
                }
//                Empty List
                viewModel.emptyList.observe(viewLifecycleOwner) { isEmpty ->
                    emptyListLayout.visibility = if (isEmpty) View.VISIBLE else View.GONE
                    moviesList.visibility = if (isEmpty) View.GONE else View.VISIBLE

                }

            }


        }

    }


}