package com.pravin.mediastreamanalytics

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp
import com.pravin.mediastreamanalytics.analytics.AnalyticsManager

class MediaStreamAnalyticsApp : Application() {
    private val tag = "MediaStreamAnalyticsApp"

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        Log.d(tag, "Firebase apps = ${FirebaseApp.getApps(this).map { it.name }}")

        AnalyticsManager.initialize(this)
        AnalyticsManager.logAppStart()
        AnalyticsManager.logDebugSmokeTest()
    }
}
