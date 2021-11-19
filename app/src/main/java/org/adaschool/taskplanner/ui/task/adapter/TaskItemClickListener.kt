package org.adaschool.taskplanner.ui.task.adapter

import org.adaschool.taskplanner.data.entity.Task

/**
 * @author Santiago Carrillo
 * 2/11/21.
 */
interface TaskItemClickListener {

    fun onTaskClicked(task: Task)
}