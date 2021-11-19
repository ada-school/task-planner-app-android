package org.adaschool.taskplanner.analytics

/**
 * @author Santiago Carrillo
 * 19/10/21.
 */
interface AnalyticsAdapter {

    fun reportEvent(name: String, data: Map<String, String>?)

}