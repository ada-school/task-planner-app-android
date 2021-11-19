package org.adaschool.taskplanner.network.service.task

import retrofit2.Response
import retrofit2.http.*

/**
 * @author Santiago Carrillo
 * 27/10/21.
 */
interface TaskService {

    @GET("api/task/all")
    suspend fun getTaskList(): Response<List<TaskDto>>

    @POST("api/task")
    suspend fun saveTask(@Body taskDto: TaskDto): Response<TaskDto>

    @PUT("api/task/{id}")
    suspend fun updateTask(@Body taskDto: TaskDto, @Path("id") id: String): Response<TaskDto>
}