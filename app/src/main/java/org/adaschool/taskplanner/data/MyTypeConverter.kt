package org.adaschool.taskplanner.data

import androidx.room.TypeConverter
import java.util.*

/**
 * @author Santiago Carrillo
 * 21/10/21.
 */


class MyTypeConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}