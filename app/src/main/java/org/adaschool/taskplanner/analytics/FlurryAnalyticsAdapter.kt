package org.adaschool.taskplanner.analytics

import com.flurry.android.FlurryAgent

/**
 * @author Santiago Carrillo
 * 19/10/21.
 */
class FlurryAnalyticsAdapter : AnalyticsAdapter {

    override fun reportEvent(name: String, data: Map<String, String>?) {
        FlurryAgent.logEvent(name, data!!)
    }

}