package com.hsalihkucuk.jetpackcodev7.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.hsalihkucuk.jetpackcodev7.data.entity.Tasks

@Dao
interface TasksDao {
    @Query("SELECT * FROM toDos")
    suspend fun getAllTasks() : List<Tasks>

    @Insert
    suspend fun save(task: Tasks)

    @Update
    suspend fun update(task: Tasks)

    @Delete
    suspend fun delete(task: Tasks)

    @Query("SELECT * FROM toDos Where name like '%'|| :searchText || '%'")
    suspend fun search(searchText: String) : List<Tasks>
}