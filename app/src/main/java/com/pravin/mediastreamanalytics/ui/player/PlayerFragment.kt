package com.pravin.mediastreamanalytics.ui.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.pravin.mediastreamanalytics.R
import com.pravin.mediastreamanalytics.analytics.AnalyticsManager
import com.pravin.mediastreamanalytics.databinding.FragmentPlayerBinding
import kotlinx.coroutines.launch

class PlayerFragment : Fragment() {

    private var _binding: FragmentPlayerBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PlayerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(top = systemBars.top, bottom = systemBars.bottom)
            insets
        }

        setupDropdown()
        setupListeners()
        observeViewModel()



        // Log default media source
        AnalyticsManager.logDefaultMediaSource(viewModel.mediaSource.value)
    }

    private fun setupDropdown() {
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            viewModel.availableSources
        )
        binding.sourceAutoComplete.setAdapter(adapter)
        binding.sourceAutoComplete.setText(viewModel.mediaSource.value, false)

        binding.sourceAutoComplete.setOnItemClickListener { _, _, position, _ ->
            val oldSource = viewModel.mediaSource.value
            val newSource = viewModel.availableSources[position]
            if (oldSource != newSource) {
                viewModel.setMediaSource(newSource)
                AnalyticsManager.logMediaSourceSwitch(oldSource, newSource)
            }
        }
    }

    private fun setupListeners() {
        binding.btnPlayPause.setOnClickListener {
            val wasPlaying = viewModel.isPlaying.value
            viewModel.togglePlayback()
            if (!wasPlaying) {
                // We just started playing
                AnalyticsManager.logMediaItemClick("Mock Track")
            }
        }

        binding.btnNext.setOnClickListener {
            AnalyticsManager.logMediaItemNext("Mock Track")
        }

        binding.btnPrevious.setOnClickListener {
            AnalyticsManager.logMediaItemPrevious("Mock Track")
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isPlaying.collect { isPlaying ->
                    val iconRes = if (isPlaying) {
                        R.drawable.outline_pause_circle_24
                    } else {
                        R.drawable.outline_play_arrow_24
                    }
                    binding.btnPlayPause.setIconResource(iconRes)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
