package com.hsalihkucuk.jetpackcodev7.data.repo

import com.hsalihkucuk.jetpackcodev7.data.datasource.TasksDataSource
import com.hsalihkucuk.jetpackcodev7.data.entity.Tasks

class TasksRepository(var tasksDataSource: TasksDataSource) {
    suspend fun getAllTasks() : List<Tasks> = tasksDataSource.getAllTasks()

    suspend fun save(taskName: String) = tasksDataSource.save(taskName)

    suspend fun update(taskId: Int, taskName: String) = tasksDataSource.update(taskId, taskName)

    suspend fun delete(taskId: Int) = tasksDataSource.delete(taskId)

    suspend fun search(searchText: String) : List<Tasks> = tasksDataSource.search(searchText)
}