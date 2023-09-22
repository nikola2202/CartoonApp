package com.example.cartoonapp.episodes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.example.cartoonapp.R
import com.example.cartoonapp.databinding.FragmentEpisodeListBinding
import com.example.cartoonapp.domain.models.Episode
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class EpisodeListFragment: Fragment(R.layout.fragment_episode_list) {

    private var _binding: FragmentEpisodeListBinding? = null
    private val binding: FragmentEpisodeListBinding by lazy {
        _binding!!
    }

    private val viewModel: EpisodesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEpisodeListBinding.bind(view)

        val epoxyController = EpisodeListEpoxyController()

        lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData: PagingData<Episode> ->
                epoxyController.submitData(pagingData)
            }
        }

        binding.epoxyRecyclerView.setController(epoxyController)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}