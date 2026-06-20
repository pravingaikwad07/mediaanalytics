package com.pravin.mediastreamanalytics.analytics

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics

object AnalyticsManager {

    private const val TAG = "AnalyticsManager"
    private var DEBUG_MODE = true

    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private lateinit var appContext: Context

    fun initialize(context: Context) {
        appContext = context.applicationContext
        firebaseAnalytics = Firebase.analytics
    }

    private fun logInternal(eventName: String, params: Bundle? = null) {
        firebaseAnalytics.logEvent(eventName, params)

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

    fun setUserId(userId: String?) {
        firebaseAnalytics.setUserId(userId)
        if (DEBUG_MODE) {
            Log.d(TAG, "User ID set to: $userId")
            Toast.makeText(appContext, "User ID: $userId", Toast.LENGTH_SHORT).show()
        }
    }

    fun setUserProperty(name: String, value: String?) {
        firebaseAnalytics.setUserProperty(name, value)
        if (DEBUG_MODE) {
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

    fun logDefaultMediaSource(sourceName: String) {
        val bundle = Bundle().apply {
            putString("source_name", sourceName)
        }
        logInternal("default_media_source", bundle)
        setUserProperty("favorite_source", sourceName)
    }

    fun logMediaSourceSwitch(oldSource: String, newSource: String) {
        val bundle = Bundle().apply {
            putString("old_source", oldSource)
            putString("new_source", newSource)
        }
        logInternal("media_source_switch", bundle)
        setUserProperty("favorite_source", newSource)
    }

    fun logMediaPlay(itemName: String, sourceName: String, action: String) {
        val bundle = Bundle().apply {
            putString(FirebaseAnalytics.Param.ITEM_NAME, itemName)
            putString("source_name", sourceName)
            putString("action", action)
        }
        logInternal("media_play", bundle)
    }

    fun logMediaPause(itemName: String, sourceName: String, action: String) {
        val bundle = Bundle().apply {
            putString(FirebaseAnalytics.Param.ITEM_NAME, itemName)
            putString("source_name", sourceName)
            putString("action", action)
        }
        logInternal("media_pause", bundle)
    }

    fun logMediaItemNext(currentItemName: String, sourceName: String, action: String) {
        val bundle = Bundle().apply {
            putString("current_item_name", currentItemName)
            putString("source_name", sourceName)
            putString("action", action)
        }
        logInternal("media_item_next", bundle)
    }

    fun logMediaItemPrevious(currentItemName: String, sourceName: String, action: String) {
        val bundle = Bundle().apply {
            putString("current_item_name", currentItemName)
            putString("source_name", sourceName)
            putString("action", action)

        }
        logInternal("media_item_previous", bundle)
    }
}
