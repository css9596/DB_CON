package com.example.db_con_project.data

import androidx.appcompat.app.AppCompatActivity

data class Feature(
    val title: String,
    val description: String,
    val demoActivity: Class<out AppCompatActivity>,
    val designSource: String,
    val javaSource: String,
    val xmlSource: String
)
