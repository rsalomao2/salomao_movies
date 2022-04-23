package com.salomao.movies.presentation.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.salomao.movies.databinding.FragmentMovieListBinding
import com.salomao.movies.domain.di.injectMovieListKoin
import com.salomao.movies.presentation.model.MovieLitItemUiState
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment() {
    private lateinit var movieAdapter: MovieListAdapter
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<MovieListViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectMovieListKoin()
        setupRecycleView()
        setupObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupObservers() {
        lifecycleScope.launchWhenCreated {
            viewModel.listLiveData.collectLatest {
                movieAdapter.submitData(it)
            }
        }
    }


    private fun setupRecycleView() {
        movieAdapter = MovieListAdapter(::onMovieClicked)
        binding.rvMovies.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
        }
    }

    private fun onMovieClicked(movieUiState: MovieLitItemUiState) {
//        findNavController().navigate(
//            R.id.action_movieListFragment_to_movieDetailsFragment,
//            bundleOf(ARGS_MOVIE_ID to movieUiState.id)
//        )
        Toast.makeText(
            requireContext(),
            "You have clicked in ${movieUiState.name}",
            Toast.LENGTH_LONG
        ).show()
    }

    companion object {
        const val ARGS_MOVIE_ID = "ARGS_MOVIE"
    }
}
