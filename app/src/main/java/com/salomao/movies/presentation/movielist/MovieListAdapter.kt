package com.salomao.movies.presentation.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.salomao.movies.R
import com.salomao.movies.databinding.LayoutMovieListItemBinding
import com.salomao.movies.presentation.model.MovieLitItemUiState

class MovieListAdapter(
    private val onItemClick: (MovieLitItemUiState) -> (Unit)
) : PagingDataAdapter<MovieLitItemUiState, MovieListAdapter.MovieViewHolder>(PHOTO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            LayoutMovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class MovieViewHolder(private val binding: LayoutMovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(itemUiState: MovieLitItemUiState) {
            binding.apply {
                tvTitle.text = itemUiState.name
                tvGenre.text = itemUiState.genre
                ratingBar.rating = itemUiState.score
                tvYear.text = itemUiState.releaseDate

                Glide.with(binding.root.context)
                    .load(itemUiState.thumbnailUrl)
                    .fitCenter()
                    .error(R.drawable.ic_empty_movie_list)
                    .into(binding.ivThumb)

                container.setOnClickListener { onItemClick(itemUiState) }
            }
        }
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<MovieLitItemUiState>() {
            override fun areItemsTheSame(
                oldItem: MovieLitItemUiState,
                newItem: MovieLitItemUiState
            ) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MovieLitItemUiState,
                newItem: MovieLitItemUiState
            ) =
                oldItem == newItem
        }
    }
}