package com.salomao.movies.presentation.delete

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.salomao.movies.R
import com.salomao.movies.databinding.FragmentCatListBinding
import com.salomao.movies.domain.di.injectCatListKoin
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.viewmodel.ext.android.viewModel


class CatListFragment : Fragment(R.layout.fragment_cat_list) {
    private val viewModel by viewModel<CatListViewModel>()
    private var _binding: FragmentCatListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectCatListKoin()
        _binding = FragmentCatListBinding.bind(view)
        val adapter = UnsplashPhotoAdapter()

        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.setHasFixedSize(true)
        }

        lifecycleScope.launchWhenCreated {
            viewModel.listLiveData.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}