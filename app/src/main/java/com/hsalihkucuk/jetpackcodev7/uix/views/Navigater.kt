package com.hsalihkucuk.jetpackcodev7.uix.views

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.hsalihkucuk.jetpackcodev7.data.entity.Tasks
import com.hsalihkucuk.jetpackcodev7.uix.viewmodel.AnaSayfaViewModel
import com.hsalihkucuk.jetpackcodev7.uix.viewmodel.DetaySayfaViewModel
import com.hsalihkucuk.jetpackcodev7.uix.viewmodel.KayitSayfaViewModel

@Composable
fun Navigater(anaSayfaViewModel : AnaSayfaViewModel, kayitSayfaViewModel : KayitSayfaViewModel, detaySayfaViewModel : DetaySayfaViewModel){
    val navControler = rememberNavController()

    NavHost(navController = navControler, startDestination = "AnaSayfa"){
        composable("AnaSayfa"){ AnaSayfa(navControler, anaSayfaViewModel) }
        composable("KayitSayfa"){ KayitSayfa(navControler, kayitSayfaViewModel) }
        composable("DetaySayfa/{task}", arguments = listOf(navArgument("task"){type = NavType.StringType})){
            val json = it.arguments?.getString("task")
            val taskNesne = Gson().fromJson(json, Tasks::class.java)

            DetaySayfa(taskNesne, navControler, detaySayfaViewModel)
        }
    }
}