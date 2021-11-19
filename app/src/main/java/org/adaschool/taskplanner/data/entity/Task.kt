package org.adaschool.taskplanner.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.adaschool.taskplanner.network.service.task.TaskDto
import java.util.*

/**
 * @author Santiago Carrillo
 * 21/10/21.
 */
@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val uid: Int?,
    var id: String?,
    var name: String,
    var description: String,
    var assignedTo: String,
    var dueDate: Date,
    var status: String
) {
    constructor(taskDto: TaskDto) : this(
        null,
        taskDto.id,
        taskDto.name,
        taskDto.description,
        taskDto.assignedTo,
        taskDto.dueDate,
        taskDto.status.name
    )

    fun update(taskDto: TaskDto) {
        name = taskDto.name
        description = taskDto.description
        assignedTo = taskDto.assignedTo
        dueDate = taskDto.dueDate
    }

}

