package org.adaschool.taskplanner.ui.task.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import org.adaschool.taskplanner.data.dao.TaskDao
import org.adaschool.taskplanner.data.entity.Task
import org.adaschool.taskplanner.network.service.task.TaskDto
import org.adaschool.taskplanner.network.service.task.TaskService
import org.adaschool.taskplanner.storage.Storage
import java.util.*
import javax.inject.Inject

/**
 * @author Santiago Carrillo
 * 2/11/21.
 */
@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskService: TaskService,
    private val taskDao: TaskDao,
    val storage: Storage,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    val taskListLiveData: LiveData<List<Task>> = taskDao.all()

    var selectedTask: Task? = null

    val selectedDateLiveData: MutableLiveData<Date> = MutableLiveData()
    val saveTaskResult: MutableLiveData<Boolean> = MutableLiveData()

    init {
        loadTaskList()
        selectedDateLiveData.postValue(Date())
    }

    private fun loadTaskList() {
        viewModelScope.launch(dispatcher) {
            try {
                val response = taskService.getTaskList()
                if (response.isSuccessful) {
                    val taskList = response.body()!!
                    for (task in taskList) {
                        val foundTask = taskDao.findById(task.id!!)
                        if (foundTask != null) {
                            foundTask.update(task)
                            taskDao.update(foundTask)
                        } else {
                            taskDao.insert(
                                Task(
                                    null,
                                    task.id,
                                    task.name,
                                    task.description,
                                    task.assignedTo,
                                    task.dueDate,
                                    task.status.name
                                )
                            )
                        }
                    }
                } else {
                    Log.d("Developer", "Request Error: ${response.errorBody().toString()}")
                }
            } catch (e: Exception) {
//                Log.e("Developer", "Error", e)
            }
        }
    }


    fun saveTask(taskDto: TaskDto) {
        viewModelScope.launch(dispatcher) {
            try {
                val response = taskService.saveTask(taskDto)
                if (response.isSuccessful) {
                    taskDao.insert(Task(response.body()!!))
                } else {
                    taskDao.insert(Task(taskDto))
                }
                saveTaskResult.postValue(response.isSuccessful)
            } catch (e: Exception) {
                taskDao.insert(Task(taskDto))
                saveTaskResult.postValue(false)
            }
        }
    }

    fun syncTasks() {
        viewModelScope.launch(dispatcher) {

            try {
                val notSyncTask = taskDao.findNotUploadedTasks()
                for (task in notSyncTask) {
                    val response = taskService.saveTask(TaskDto(task))
                    if (response.isSuccessful) {
                        task.id = response.body()!!.id
                        taskDao.update(task)
                    }
                }
            } catch (e: Exception) {
                Log.e("Developer", "Error", e)
            }
        }
    }
}
