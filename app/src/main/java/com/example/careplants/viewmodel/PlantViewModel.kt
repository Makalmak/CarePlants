package com.example.careplants.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.careplants.data.PlantDatabase
import com.example.careplants.repository.PlantRepository
import com.example.careplants.model.Plant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlantViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Plant>>
    private val repository: PlantRepository

    init {
        val plantDAO = PlantDatabase.getDatabase(application).plantDao()
        repository = PlantRepository(plantDAO)
        readAllData = repository.readAllData
    }

    fun addPlant(plant: Plant){
        viewModelScope.launch(Dispatchers.IO){
            repository.addPlant(plant)
        }

    }

    fun updatePlant(plant: Plant){
        viewModelScope.launch(Dispatchers.IO){
            repository.updatePlant(plant)
        }
    }

    fun deletePlant(plant: Plant){
        viewModelScope.launch(Dispatchers.IO){
            repository.deletePlant(plant)
        }
    }
}