package com.example.careplants.data

import androidx.lifecycle.LiveData
import com.example.careplants.data.Plant
import com.example.careplants.data.PlantDAO

class PlantRepository( private val plantDAO: PlantDAO) {

    val readAllData: LiveData<List<Plant>> = plantDAO.readAllData()

    suspend fun addPlant(plant: Plant){
        plantDAO.addPlant(plant)
    }
}