package org.adaschool.myapplication

import android.content.Context
import androidx.annotation.StringRes
import androidx.test.platform.app.InstrumentationRegistry

/**
 * @author Santiago Carrillo
 * 22/11/21.
 */
object TestsUtils {

    fun getResourceString(@StringRes id: Int): String {
        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        return targetContext.resources.getString(id)
    }
}