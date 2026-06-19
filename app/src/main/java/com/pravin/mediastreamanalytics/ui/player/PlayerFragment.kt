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
            viewModel.togglePlayback()
            if (viewModel.isPlaying.value) {
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
                    // For demo, we just use a generic icon change logic
                    // In real app, we would change the actual icon
                    binding.btnPlayPause.alpha = if (isPlaying) 1.0f else 0.7f
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
