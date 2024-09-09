package com.hsalihkucuk.jetpackcodev7.uix.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hsalihkucuk.jetpackcodev7.data.entity.Tasks
import com.hsalihkucuk.jetpackcodev7.uix.viewmodel.DetaySayfaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetaySayfa(task: Tasks, navController: NavController, detaySayfaViewModel: DetaySayfaViewModel){
    val taskName = remember { mutableStateOf("") }

    LaunchedEffect(key1 = true) {
        taskName.value = task.taskName
    }

    Scaffold(topBar = { CenterAlignedTopAppBar(title = { Text(text = "Görev Detay") }) }) { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
            TextField(value = taskName.value, onValueChange = {taskName.value = it}, label = { Text(text = "Görev İsmi:") })
            Button(onClick = {
                detaySayfaViewModel.update(task.taskId, taskName.value)
                navController.popBackStack()
            }, Modifier.size(150.dp, 40.dp)) {
                Text(text = "Güncelle", fontSize = 18.sp)
            }
        }
    }
}