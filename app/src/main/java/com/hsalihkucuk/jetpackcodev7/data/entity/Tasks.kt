package com.hsalihkucuk.jetpackcodev7.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "toDos")
data class Tasks(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") @NotNull var taskId: Int, @ColumnInfo(name = "name") @NotNull var taskName: String)