package com.hsalihkucuk.jetpackcodev7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.hsalihkucuk.jetpackcodev7.ui.theme.JetpackCOdev7Theme
import com.hsalihkucuk.jetpackcodev7.uix.viewmodel.AnaSayfaViewModel
import com.hsalihkucuk.jetpackcodev7.uix.viewmodel.DetaySayfaViewModel
import com.hsalihkucuk.jetpackcodev7.uix.viewmodel.KayitSayfaViewModel
import com.hsalihkucuk.jetpackcodev7.uix.views.Navigater
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val anaSayfaViewModel : AnaSayfaViewModel by viewModels()
    val detaySayfaViewModel : DetaySayfaViewModel by viewModels()
    val kayitSayfaViewModel : KayitSayfaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackCOdev7Theme {
                Navigater(anaSayfaViewModel, kayitSayfaViewModel, detaySayfaViewModel)
            }
        }
    }
}