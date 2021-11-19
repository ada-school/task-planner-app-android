package org.adaschool.taskplanner.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import org.adaschool.taskplanner.data.entity.Task

/**
 * @author Santiago Carrillo
 * 21/10/21.
 */
@Dao
interface TaskDao {

    @Query("SELECT * from task")
    fun all(): LiveData<List<Task>>

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Query("SELECT * from task WHERE id = :id")
    fun findById(id: String): Task?

    @Query("SELECT * from task WHERE id IS NULL")
    fun findNotUploadedTasks(): List<Task>
}