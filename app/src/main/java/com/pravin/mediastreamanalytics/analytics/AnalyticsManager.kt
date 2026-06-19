package com.pravin.mediastreamanalytics.analytics

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics

/**
 * Singleton class to manage Firebase Analytics events.
 */
object AnalyticsManager {

    private const val TAG = "AnalyticsManager"
    private var DEBUG_MODE = true // Toggle for Logs and Toasts

    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private lateinit var appContext: Context

    fun initialize(context: Context) {
        appContext = context.applicationContext
        firebaseAnalytics = Firebase.analytics
    }

    private fun logInternal(eventName: String, params: Bundle? = null) {
        // Actual Firebase Logging
        firebaseAnalytics.logEvent(eventName, params)

        // Debug Logs and Toasts
        if (DEBUG_MODE) {
            val message = "Event: $eventName | Params: ${params?.let { bundleToMap(it) } ?: "null"}"
            Log.d(TAG, message)
            Toast.makeText(appContext, "Logged: $eventName", Toast.LENGTH_SHORT).show()
        }
    }

    private fun bundleToMap(bundle: Bundle): Map<String, Any?> {
        val map = mutableMapOf<String, Any?>()
        for (key in bundle.keySet()) {
            map[key] = bundle.get(key)
        }
        return map
    }

    /**
     * Set the user ID for analytics tracking.
     */
    fun setUserId(userId: String?) {
        firebaseAnalytics.setUserId(userId)
        if (DEBUG_MODE) {
            Log.d(TAG, "User ID set to: $userId")
            Toast.makeText(appContext, "User ID: $userId", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Log the app start event.
     */
    fun logAppStart() {
        logInternal("app_start")
    }

    /**
     * Log the default media source selected on start.
     */
    fun logDefaultMediaSource(sourceName: String) {
        val bundle = Bundle().apply {
            putString("source_name", sourceName)
        }
        logInternal("default_media_source", bundle)
    }

    /**
     * Log media source switch event.
     */
    fun logMediaSourceSwitch(oldSource: String, newSource: String) {
        val bundle = Bundle().apply {
            putString("old_source", oldSource)
            putString("new_source", newSource)
        }
        logInternal("media_source_switch", bundle)
    }

    /**
     * Log media item click event.
     */
    fun logMediaItemClick(itemName: String) {
        val bundle = Bundle().apply {
            putString(FirebaseAnalytics.Param.ITEM_NAME, itemName)
        }
        logInternal("media_item_click", bundle)
    }

    /**
     * Log media item next event.
     */
    fun logMediaItemNext(currentItemName: String) {
        val bundle = Bundle().apply {
            putString("current_item_name", currentItemName)
        }
        logInternal("media_item_next", bundle)
    }

    /**
     * Log media item previous event.
     */
    fun logMediaItemPrevious(currentItemName: String) {
        val bundle = Bundle().apply {
            putString("current_item_name", currentItemName)
        }
        logInternal("media_item_previous", bundle)
    }
}
