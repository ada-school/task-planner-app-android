package org.adaschool.taskplanner.analytics

import android.util.Log

/**
 * @author Santiago Carrillo
 * 19/10/21.
 */
class GoogleAnalyticsAdapter : AnalyticsAdapter {
    override fun reportEvent(name: String, data: Map<String, String>?) {
        Log.d("Developer", "Logging event:$name to Google Analytics")
    }
}