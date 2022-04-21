package com.salomao.movies.presentation.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.salomao.movies.R
import com.salomao.movies.databinding.FragmentMovieListBinding
import com.salomao.movies.domain.di.injectMovieListKoin
import com.salomao.movies.domain.model.MovieModel
import org.koin.android.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment() {
    private lateinit var movieAdapter: MoviesAdapter
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<MovieListViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        injectMovieListKoin()
        _binding = FragmentMovieListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        setupObservers()
        viewModel.getMovieList()
    }

    private fun setupObservers() {
        observeMovieList()
        observeErrorMessages()
        observeShowEmptyListView()
        observeLoadingView()
    }

    private fun observeLoadingView() {
        viewModel.showLoadingLiveData.observe(viewLifecycleOwner){ show ->
            binding.progressBar.container.isVisible = show
        }
    }

    private fun observeShowEmptyListView() {
        viewModel.showEmptyLiveData.observe(viewLifecycleOwner) { show ->
            binding.emptyMovieList.container.isVisible = show
        }
    }

    private fun observeErrorMessages() {
        viewModel.errorMessageLiveData.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun observeMovieList() {
        viewModel.movieListLiveData.observe(viewLifecycleOwner) { newList ->
            movieAdapter.updateMovies(newList)
        }
    }

    private fun setupRecycleView() {
        movieAdapter = MoviesAdapter(::onMovieClicked)
        binding.rvMovies.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun onMovieClicked(movieModel: MovieModel) {
        findNavController().navigate(
            R.id.action_movieListFragment_to_movieDetailsFragment,
            bundleOf(ARGS_MOVIE_ID to movieModel.id)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val ARGS_MOVIE_ID = "ARGS_MOVIE"
    }
}
