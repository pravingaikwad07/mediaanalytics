package com.pravin.mediastreamanalytics

import android.app.Application
import com.pravin.mediastreamanalytics.analytics.AnalyticsManager

class MediaStreamAnalyticsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AnalyticsManager.initialize(this)
        AnalyticsManager.logAppStart()
    }
}
