package com.hsalihkucuk.jetpackcodev7.uix.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hsalihkucuk.jetpackcodev7.data.entity.Tasks
import com.hsalihkucuk.jetpackcodev7.data.repo.TasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnaSayfaViewModel @Inject constructor(var taskRepository : TasksRepository) : ViewModel() {
    var taskList = MutableLiveData<List<Tasks>>()

    init {
        getAllTasks()
    }

    fun getAllTasks(){
        CoroutineScope(Dispatchers.Main).launch {
            taskList.value = taskRepository.getAllTasks()
        }
    }

    fun search(searchText: String){
        CoroutineScope(Dispatchers.Main).launch {
            taskList.value = taskRepository.search(searchText)
        }
    }

    fun delete(taskId: Int){
        CoroutineScope(Dispatchers.Main).launch {
            taskRepository.delete(taskId)
            getAllTasks()
        }
    }
}