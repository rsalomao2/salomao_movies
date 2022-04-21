package com.salomao.movies.presentation.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.salomao.movies.R
import com.salomao.movies.databinding.LayoutMovieListItemBinding
import com.salomao.movies.domain.model.MovieModel

class MoviesAdapter(
    private val onItemClick: (MovieModel) -> (Unit)
) : RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {
    private var items: MutableList<MovieModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<LayoutMovieListItemBinding>(
            inflater,
            R.layout.layout_movie_list_item,
            parent,
            false
        )

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateMovies(itemsList: List<MovieModel>) {
        this.items.clear()
        this.items.addAll(itemsList)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(private val binding: LayoutMovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieModel: MovieModel) {
            binding.tvName.text = movieModel.name
            binding.container.setOnClickListener { onItemClick(movieModel) }
        }
    }
}
