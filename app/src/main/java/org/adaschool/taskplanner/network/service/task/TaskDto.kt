package org.adaschool.taskplanner.network.service.task

import org.adaschool.taskplanner.data.entity.Task
import java.util.*

/**
 * @author Santiago Carrillo
 * 27/10/21.
 */
data class TaskDto(
    val id: String?,
    val name: String,
    val description: String,
    val status: StatusEnum,
    val assignedTo: String,
    val dueDate: Date
) {
    constructor(task: Task) : this(
        null,
        task.name,
        task.description,
        StatusEnum.valueOf(task.status),
        task.assignedTo,
        task.dueDate
    )
}