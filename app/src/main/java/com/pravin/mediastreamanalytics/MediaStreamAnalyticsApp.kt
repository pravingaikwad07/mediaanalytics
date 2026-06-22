package com.pravin.mediastreamanalytics

import android.app.Application
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent
import com.pravin.mediastreamanalytics.analytics.AnalyticsManager

class MediaStreamAnalyticsApp : Application() {
    private  val TAG = "MediaStreamAnalyticsApp"
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        Log.d(TAG, "Apps = ${FirebaseApp.getApps(this)}")

        AnalyticsManager.initialize(this)
        AnalyticsManager.logAppStart()

        Firebase.analytics.setAnalyticsCollectionEnabled(true)
        Firebase.analytics.logEvent("test_event") {
            param("test_param", "hello")
        }
        Log.d("ANALYTICS_TEST", "test_event fired")
    }
}
