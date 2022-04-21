package com.salomao.movies.presentation.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.salomao.movies.databinding.FragmentMovieDetailsBinding
import com.salomao.movies.domain.di.injectMovieDetailKoin
import com.salomao.movies.presentation.movielist.MovieListFragment.Companion.ARGS_MOVIE_ID
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailsFragment : Fragment() {
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<MovieDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        injectMovieDetailKoin()
        _binding = FragmentMovieDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        val movieClicked = arguments?.getInt(ARGS_MOVIE_ID)
        viewModel.getMovie(movieClicked)
    }

    private fun setupObservers() {
        viewModel.movieDetailLiveData.observe(viewLifecycleOwner) { clickedMovie ->
            binding.tvTitle.text = clickedMovie.name
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
