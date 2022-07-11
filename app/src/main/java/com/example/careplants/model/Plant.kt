package com.example.careplants.model

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.sql.Time

@Parcelize
@Entity(tableName = "plant_table")
data class Plant(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val info: String,
    //val photo: Bitmap,
    val wat_interval: String,
    val notif_time: String
): Parcelable