package com.example.androiddevchallenge.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.model.Animal
import com.example.androiddevchallenge.data.repository.AnimalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AnimalsViewModel : ViewModel() {
    private val animalRepository = AnimalRepository()

    private val _animalList = MutableLiveData(emptyList<Animal>())
    val animalList: LiveData<List<Animal>> get() = _animalList

    private val _animalFilter = MutableLiveData(AnimalFilter.ALL)
    val animalFilter: LiveData<AnimalFilter> get() = _animalFilter

    init {
        viewModelScope.launch(Dispatchers.IO) {
            animalRepository.getAll().collect {
                println(it.size)
                _animalList.postValue(it)
            }
        }
    }

    fun getAnimalById(id: Long) = animalRepository.getById(id)
}

enum class AnimalFilter {
    ALL, DOG, CAT
}