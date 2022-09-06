package com.example.imdbtopmovies.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdbtopmovies.databinding.FragmentFavoriteBinding
import com.example.imdbtopmovies.repository.FavoriteRepository
import com.example.imdbtopmovies.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {


    //Binging
    private lateinit var binding: FragmentFavoriteBinding

    @Inject
    lateinit var repository: FavoriteRepository

    @Inject
    lateinit var favoriteAdapter: FavoriteAdapter

    val viewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavorites()

        //Init Views
        binding.apply {
            //Get Favorite
            viewModel.favoriteList.observe(viewLifecycleOwner) {
                favoriteAdapter.setData(it)
            }
            //Init Recycler
            favoriteList.apply {
                adapter = favoriteAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
            //Visibility
            viewModel.empty.observe(viewLifecycleOwner) { isEmpty ->
                favoriteList.visibility = if (isEmpty) View.GONE else View.VISIBLE
                emptyListLayout.visibility = if (isEmpty) View.VISIBLE else View.GONE
            }

            //Click
            favoriteAdapter.setOnClickListener {
                val direction = FavoriteFragmentDirections.actionToDetailFragment(it.id)
                findNavController().navigate(direction)
            }
        }

    }

}