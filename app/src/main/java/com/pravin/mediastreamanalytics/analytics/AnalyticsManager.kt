package com.pravin.mediastreamanalytics.analytics

import android.content.Context
import android.os.Bundle
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics

/**
 * Singleton class to manage Firebase Analytics events.
 */
object AnalyticsManager {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    fun initialize(context: Context) {
        firebaseAnalytics = Firebase.analytics
    }

    /**
     * Set the user ID for analytics tracking.
     */
    fun setUserId(userId: String?) {
        firebaseAnalytics.setUserId(userId)
    }

    /**
     * Log the app start event.
     */
    fun logAppStart() {
        firebaseAnalytics.logEvent("app_start", null)
    }

    /**
     * Log the default media source selected on start.
     */
    fun logDefaultMediaSource(sourceName: String) {
        val bundle = Bundle().apply {
            putString("source_name", sourceName)
        }
        firebaseAnalytics.logEvent("default_media_source", bundle)
    }

    /**
     * Log media source switch event.
     */
    fun logMediaSourceSwitch(oldSource: String, newSource: String) {
        val bundle = Bundle().apply {
            putString("old_source", oldSource)
            putString("new_source", newSource)
        }
        firebaseAnalytics.logEvent("media_source_switch", bundle)
    }

    /**
     * Log media item click event.
     */
    fun logMediaItemClick(itemName: String) {
        val bundle = Bundle().apply {
            putString(FirebaseAnalytics.Param.ITEM_NAME, itemName)
        }
        firebaseAnalytics.logEvent("media_item_click", bundle)
    }

    /**
     * Log media item next event.
     */
    fun logMediaItemNext(currentItemName: String) {
        val bundle = Bundle().apply {
            putString("current_item_name", currentItemName)
        }
        firebaseAnalytics.logEvent("media_item_next", bundle)
    }

    /**
     * Log media item previous event.
     */
    fun logMediaItemPrevious(currentItemName: String) {
        val bundle = Bundle().apply {
            putString("current_item_name", currentItemName)
        }
        firebaseAnalytics.logEvent("media_item_previous", bundle)
    }
}
