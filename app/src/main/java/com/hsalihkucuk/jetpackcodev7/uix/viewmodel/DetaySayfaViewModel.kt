package com.hsalihkucuk.jetpackcodev7.uix.viewmodel

import androidx.lifecycle.ViewModel
import com.hsalihkucuk.jetpackcodev7.data.repo.TasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetaySayfaViewModel @Inject constructor(var taskRepository : TasksRepository) : ViewModel() {
    fun update(taskId: Int, taskName: String){
        CoroutineScope(Dispatchers.Main).launch {
            taskRepository.update(taskId, taskName)
        }
    }
}