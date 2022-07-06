package com.example.careplants.data

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plant_table")
data class Plant(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val info: String,
    val photo: Bitmap,
    val wat_interval: Int
)