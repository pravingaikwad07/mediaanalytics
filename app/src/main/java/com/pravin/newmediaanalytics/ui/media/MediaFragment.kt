package com.pravin.newmediaanalytics.ui.media

import android.graphics.Color
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
import com.pravin.newmediaanalytics.BuildConfig
import com.pravin.newmediaanalytics.R
import com.pravin.newmediaanalytics.analytics.AnalyticsManager
import com.pravin.newmediaanalytics.databinding.FragmentMediaBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random

class MediaFragment : Fragment() {

    private var _binding: FragmentMediaBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MediaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMediaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(top = systemBars.top, bottom = systemBars.bottom)
            insets
        }

        val userName = arguments?.getString("userName") ?: "Unknown"
        binding.tvUser.text = userName
        binding.tvAppVersion.text = "Version: ${BuildConfig.VERSION_NAME}"

        setupDropdown()
        setupListeners()
        observeViewModel()

        AnalyticsManager.logScreenView("Media Screen", "MediaFragment")
        AnalyticsManager.logMediaSourceSelected(viewModel.mediaSource.value, viewModel.mediaSource.value, "default")
    }

    private fun setupDropdown() {
        val adapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            viewModel.availableSources
        )
        binding.sourceAutoComplete.setAdapter(adapter)
        binding.sourceAutoComplete.setText(viewModel.mediaSource.value, false)

        binding.sourceAutoComplete.setOnItemClickListener { _, _, position, _ ->
            val oldSource = viewModel.mediaSource.value
            val newSource = viewModel.availableSources[position]
            if (oldSource != newSource) {
                viewModel.setMediaSource(newSource)
                AnalyticsManager.logMediaSourceSelected(oldSource, newSource, "dropdown")
                logEvent("Source switch: $newSource")
            }
        }
    }

    private fun setupListeners() {
        binding.btnPlayPause.setOnClickListener {
            val wasPlaying = viewModel.isPlaying.value
            viewModel.togglePlayback()
            val actionText = if (!wasPlaying) "media_play" else "media_pause"
            updatePlaceholder(actionText)
            logEvent(actionText)
            AnalyticsManager.logMediaInteraction(actionText, viewModel.mediaSource.value)
        }

        binding.btnNext.setOnClickListener {
            updatePlaceholder("media_next")
            logEvent("media_next")
            AnalyticsManager.logMediaInteraction("media_next", viewModel.mediaSource.value)
        }

        binding.btnPrevious.setOnClickListener {
            updatePlaceholder("media_previous")
            logEvent("media_previous")
            AnalyticsManager.logMediaInteraction("media_previous", viewModel.mediaSource.value)
        }
    }

    private fun updatePlaceholder(text: String) {
        binding.mediaPlaceholder.text = text
        binding.mediaPlaceholder.setBackgroundColor(getRandomColor())
    }

    private fun logEvent(event: String) {
        val timestamp = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        val newLog = "[$timestamp] $event\n${binding.tvEventLog.text}"
        binding.tvEventLog.text = newLog
    }

    private fun getRandomColor(): Int {
        val r = Random.nextInt(180, 256)
        val g = Random.nextInt(180, 256)
        val b = Random.nextInt(180, 256)
        return Color.rgb(r, g, b)
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
