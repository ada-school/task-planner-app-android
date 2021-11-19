package org.adaschool.taskplanner

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.adaschool.taskplanner.data.dao.TaskDao
import org.adaschool.taskplanner.network.service.task.StatusEnum
import org.adaschool.taskplanner.network.service.task.TaskDto
import org.adaschool.taskplanner.network.service.task.TaskService
import org.adaschool.taskplanner.storage.Storage
import org.adaschool.taskplanner.ui.task.viewmodel.TaskViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

/**
 * @author Santiago Carrillo
 * 8/11/21.
 */
@RunWith(MockitoJUnitRunner::class)
class TaskViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var taskService: TaskService

    @Mock
    lateinit var taskDao: TaskDao

    @Mock
    lateinit var storage: Storage

    lateinit var taskViewModel: TaskViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        taskViewModel = TaskViewModel(taskService, taskDao, storage, mainCoroutineRule.dispatcher)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `when save task returns exception notifies livedata false and saves task with taskDao `() =
        mainCoroutineRule.runBlockingTest {

            val taskDto = TaskDto(null, "", "", StatusEnum.DONE, "", Date())
            `when`(taskService.saveTask(taskDto)).thenThrow(RuntimeException::class.java)

            val observer = Observer<Boolean> {}
            try {
                taskViewModel.saveTaskResult.observeForever(observer)
                taskViewModel.saveTask(taskDto)

                verify(taskDao).insert(MockitoHelper.anyObject())

                val value = taskViewModel.saveTaskResult.value
                Assert.assertFalse(value!!)

            } finally {
                taskViewModel.saveTaskResult.removeObserver(observer)
            }

        }


}