package com.hsalihkucuk.jetpackcodev7.uix.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.hsalihkucuk.jetpackcodev7.R
import com.hsalihkucuk.jetpackcodev7.data.entity.Tasks
import com.hsalihkucuk.jetpackcodev7.uix.viewmodel.AnaSayfaViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnaSayfa(navController: NavController, anaSayfaViewModel: AnaSayfaViewModel){
    val searchState = remember { mutableStateOf(false) }
    val tfSearch = remember { mutableStateOf("") }
    val taskList = anaSayfaViewModel.taskList.observeAsState(listOf())
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        anaSayfaViewModel.getAllTasks()
    }

    fun deleteTask(dlttask : Tasks){
        scope.launch {
            val sb = snackbarHostState.showSnackbar(message = "${dlttask.taskName} görev silinsin mi?", actionLabel = "Evet", withDismissAction = true)

            if (sb == SnackbarResult.ActionPerformed) {
                anaSayfaViewModel.delete(dlttask.taskId)
            }
        }
    }

    Scaffold(topBar = { TopAppBar(title = {
        if (searchState.value) {
            TextField(value = tfSearch.value, onValueChange = {
                tfSearch.value = it
                anaSayfaViewModel.search(it)
            }, label = {Text(text = "Ara", fontSize = 22.sp)}, colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedLabelColor = Color.White,
                focusedIndicatorColor = Color.White,
                unfocusedLabelColor = Color.White,
                unfocusedIndicatorColor = Color.White,))
        }
        else Text(text = "ToDos") },
        actions = {
            if (searchState.value) {
                IconButton(onClick = {
                    searchState.value = false
                    tfSearch.value = ""
                    anaSayfaViewModel.getAllTasks()
                },content = {Icon(painter = painterResource(id = R.drawable.clear_icon),contentDescription = "Close Icon")})
            }
            else IconButton(onClick = { searchState.value = true },content = {Icon(painter = painterResource(id = R.drawable.search_icon),contentDescription = "Search Icon")})
        }) },
        floatingActionButton = { FloatingActionButton(
            onClick = { navController.navigate("KayitSayfa") },
            content = { Icon(painter = painterResource(id = R.drawable.fabbtn_icon),contentDescription = "FabBtn Icon") }) },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }) { paddingValues ->
        LazyColumn(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            items(count = taskList.value.count(), itemContent = {
                val task = taskList.value[it]
                val checkboxState = remember {mutableStateOf(false)}
                Card(modifier = Modifier.padding(all = 5.dp)) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val taskJson = Gson().toJson(task)
                            navController.navigate("DetaySayfa/$taskJson")
                        },horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {

                        Row(modifier = Modifier.padding(all = 10.dp), verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = checkboxState.value, onCheckedChange = {checkboxState.value = it})
                            if (checkboxState.value){
                                deleteTask(task)
                                Text(text = task.taskName, fontSize = 22.sp, textDecoration = TextDecoration.LineThrough)
                            }
                            else Text(text = task.taskName, fontSize = 22.sp)
                        }
                        IconButton(onClick = {
                            deleteTask(task)
                        }) {Icon(painter = painterResource(id = R.drawable.clear_icon),contentDescription = "Clear Icon", tint = Color.Gray)}
                    }
                }
            })
        }
    }
}