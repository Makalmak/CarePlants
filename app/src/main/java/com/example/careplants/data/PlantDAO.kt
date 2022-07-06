package com.example.careplants.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PlantDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPlant(plant: Plant)

    @Update
    suspend fun updatePlant(plant: Plant)

    @Query("SELECT * FROM plant_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Plant>>
}