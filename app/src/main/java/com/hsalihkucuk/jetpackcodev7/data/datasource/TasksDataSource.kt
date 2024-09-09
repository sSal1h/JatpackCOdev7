package com.hsalihkucuk.jetpackcodev7.data.datasource

import com.hsalihkucuk.jetpackcodev7.data.entity.Tasks
import com.hsalihkucuk.jetpackcodev7.room.TasksDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TasksDataSource(var tasksDao: TasksDao) {
    suspend fun getAllTasks() : List<Tasks> = withContext(Dispatchers.IO){
        return@withContext tasksDao.getAllTasks()
    }

    suspend fun save(taskName: String){
        val newTask = Tasks(0, taskName)
        tasksDao.save(newTask)
    }

    suspend fun update(taskId: Int, taskName: String){
        val updatedTask = Tasks(taskId, taskName)
        tasksDao.update(updatedTask)
    }

    suspend fun search(searchText: String) : List<Tasks> = withContext(Dispatchers.IO){
        return@withContext tasksDao.search(searchText)
    }

    suspend fun delete(taskId: Int){
        val deletedTask = Tasks(taskId,"")
        tasksDao.delete(deletedTask)
    }
}