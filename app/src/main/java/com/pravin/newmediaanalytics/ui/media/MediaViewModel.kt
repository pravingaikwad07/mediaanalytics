package com.pravin.newmediaanalytics.ui.media

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MediaViewModel : ViewModel() {

    private val _mediaSource = MutableStateFlow("Spotify")
    val mediaSource: StateFlow<String> = _mediaSource.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    val availableSources = listOf("Spotify", "YouTube", "Gaana", "SiriusXM", "Radio")

    fun setMediaSource(source: String) {
        _mediaSource.value = source
    }

    fun togglePlayback() {
        _isPlaying.value = !_isPlaying.value
    }
}
