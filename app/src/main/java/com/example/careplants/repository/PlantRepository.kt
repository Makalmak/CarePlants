package com.example.careplants.repository

import androidx.lifecycle.LiveData
import com.example.careplants.data.PlantDAO
import com.example.careplants.model.Plant

class PlantRepository( private val plantDAO: PlantDAO) {

    val readAllData: LiveData<List<Plant>> = plantDAO.readAllData()

    suspend fun addPlant(plant: Plant){
        plantDAO.addPlant(plant)
    }

    suspend fun updatePlant(plant: Plant){
        plantDAO.updatePlant(plant)
    }
}