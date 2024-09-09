package com.hsalihkucuk.jetpackcodev7.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hsalihkucuk.jetpackcodev7.data.entity.Tasks

@Database(entities = [Tasks::class], version = 1)
abstract class TasksDb : RoomDatabase() {
    abstract fun getTasksDao() : TasksDao
}