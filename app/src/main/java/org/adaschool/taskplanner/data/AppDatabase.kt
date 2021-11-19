package org.adaschool.taskplanner.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.adaschool.taskplanner.data.dao.TaskDao
import org.adaschool.taskplanner.data.entity.Task

/**
 * @author Santiago Carrillo
 * 21/10/21.
 */
@Database(entities = [Task::class], version = 1)
@TypeConverters(MyTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}
