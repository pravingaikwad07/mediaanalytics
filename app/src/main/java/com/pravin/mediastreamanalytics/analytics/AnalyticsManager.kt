package com.pravin.mediastreamanalytics.analytics

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.pravin.mediastreamanalytics.BuildConfig

object AnalyticsManager {

    private const val TAG = "AnalyticsManager"

    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private var isInitialized = false

    fun initialize(context: Context) {
        firebaseAnalytics = Firebase.analytics
        firebaseAnalytics.setAnalyticsCollectionEnabled(true)
        isInitialized = true

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "Firebase Analytics initialized for ${context.packageName}")
        }
    }

    private fun logInternal(eventName: String, params: Bundle? = null) {
        if (!isInitialized) {
            Log.w(TAG, "Skipping '$eventName' because Firebase Analytics is not initialized")
            return
        }

        firebaseAnalytics.logEvent(eventName, params)

        if (BuildConfig.DEBUG) {
            val message = "Event: $eventName | Params: ${params?.let { bundleToMap(it) } ?: "null"}"
            Log.d(TAG, message)
        }
    }

    private fun bundleToMap(bundle: Bundle): Map<String, Any?> {
        val map = mutableMapOf<String, Any?>()
        for (key in bundle.keySet()) {
            @Suppress("DEPRECATION")
            map[key] = bundle.get(key)
        }
        return map
    }

    fun setUserId(userId: String?) {
        if (!isInitialized) {
            Log.w(TAG, "Skipping user ID update because Firebase Analytics is not initialized")
            return
        }

        firebaseAnalytics.setUserId(userId)
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "User ID set to: $userId")
        }
    }

    fun setUserProperty(name: String, value: String?) {
        if (!isInitialized) {
            Log.w(
                TAG,
                "Skipping user property update because Firebase Analytics is not initialized"
            )
            return
        }

        firebaseAnalytics.setUserProperty(name, value)
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "User Property set: $name = $value")
        }
    }

    fun logScreenView(screenName: String, screenClass: String?) {
        val bundle = Bundle().apply {
            putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
            putString(FirebaseAnalytics.Param.SCREEN_CLASS, screenClass)
        }
        logInternal(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }

    fun logAppStart() {
        logInternal("app_start")
    }

    fun logDebugSmokeTest() {
        if (!BuildConfig.DEBUG) return

        val bundle = Bundle().apply {
            putString("test_param", "hello")
        }
        logInternal("debug_smoke_test", bundle)
    }

    fun logMediaSourceSelected(oldSource: String, newSource: String, selectionType: String) {
        val bundle = Bundle().apply {
            putString("key_media_source_old", oldSource)
            putString("key_media_source", newSource)
            putString("key_media_selection_type", selectionType)
        }
        logInternal("event_name_media_dropdown", bundle)
    }

    fun logMediaInteraction(action: String, sourceName: String) {
        val bundle = Bundle().apply {
            putString("key_media_source", sourceName)
            putString("key_media_action", action)
        }
        logInternal("event_name_media_button_actions", bundle)
    }
}
