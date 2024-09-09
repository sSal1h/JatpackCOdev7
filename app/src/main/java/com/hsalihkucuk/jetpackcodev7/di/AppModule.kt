package com.hsalihkucuk.jetpackcodev7.di

import android.content.Context
import androidx.room.Room
import com.hsalihkucuk.jetpackcodev7.data.datasource.TasksDataSource
import com.hsalihkucuk.jetpackcodev7.data.repo.TasksRepository
import com.hsalihkucuk.jetpackcodev7.room.TasksDao
import com.hsalihkucuk.jetpackcodev7.room.TasksDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideTasksRepository(tasksDataSource: TasksDataSource) : TasksRepository{
        return TasksRepository(tasksDataSource)
    }

    @Provides
    @Singleton
    fun provideTasksDataSource(tasksDao: TasksDao) : TasksDataSource{
        return TasksDataSource(tasksDao)
    }

    @Provides
    @Singleton
    fun provideTasksDao(@ApplicationContext context: Context) : TasksDao {
        val db = Room.databaseBuilder(context, TasksDb::class.java, "tasks.sqlite").build()
        return db.getTasksDao()
    }
}