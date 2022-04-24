package com.salomao.movies.presentation.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.salomao.movies.R
import com.salomao.movies.databinding.FragmentMovieDetailsBinding
import com.salomao.movies.domain.di.injectMovieDetailKoin
import com.salomao.movies.presentation.model.MovieDetailUiState
import com.salomao.movies.presentation.movielist.MovieListFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailsFragment : Fragment() {
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<MovieDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectMovieDetailKoin()
        val clickedMovieId = arguments?.getInt(MovieListFragment.ARGS_MOVIE_ID)
        viewModel.loadMovieDetail(clickedMovieId)
        setupObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupObservers() {
        observeMovieDetail()
        observeErrorMessage()
    }

    private fun observeErrorMessage() {
        viewModel.errorMessageFlow.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun observeMovieDetail() {
        viewModel.movieDetailFlow.observe(viewLifecycleOwner) {
            setupMovieDetailView(it)
        }
    }

    private fun setupMovieDetailView(movie: MovieDetailUiState) {
        binding.apply {
            Glide.with(requireContext())
                .load(movie.thumbnailUrl)
                .centerInside()
                .error(R.drawable.ic_empty_movie_list)
                .into(binding.ivThumbnail)

            tvTitle.text = movie.name
            tvGenre.text = movie.genre
            tvRuntime.text = movie.runTime
            tvHomePage.text = movie.homepageUrl
            tvOverview.text = movie.overview
            tvYear.text = movie.releaseYear
            ratingBar.rating = movie.score
        }
    }
}
