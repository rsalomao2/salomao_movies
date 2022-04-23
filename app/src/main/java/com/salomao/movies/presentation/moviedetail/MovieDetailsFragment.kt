package com.salomao.movies.presentation.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.salomao.movies.databinding.FragmentMovieDetailsBinding
import com.salomao.movies.presentation.movielist.MovieListFragment

class MovieDetailsFragment : Fragment() {
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val clickedMovieId = arguments?.getInt(MovieListFragment.ARGS_MOVIE_ID)
        binding.tvTitle.text = "Clicked Item ID: $clickedMovieId"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
