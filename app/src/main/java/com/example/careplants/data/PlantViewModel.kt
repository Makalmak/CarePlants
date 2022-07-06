package com.example.careplants.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlantViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Plant>>
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
}