package com.hsalihkucuk.jetpackcodev7.uix.viewmodel

import androidx.lifecycle.ViewModel
import com.hsalihkucuk.jetpackcodev7.data.repo.TasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KayitSayfaViewModel @Inject constructor(var taskRepository : TasksRepository) : ViewModel() {
    fun save(taskName: String){
        CoroutineScope(Dispatchers.Main).launch {
            taskRepository.save(taskName)
        }
    }
}