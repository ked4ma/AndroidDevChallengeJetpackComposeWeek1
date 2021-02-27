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
    private val animalRepository = AnimalRepository

    private val _animalList = MutableLiveData(emptyList<Animal>())
    val animalList: LiveData<List<Animal>> get() = _animalList

    private val _animalFilter = MutableLiveData(AnimalFilter.ALL)
    val animalFilter: LiveData<AnimalFilter> get() = _animalFilter

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch(Dispatchers.IO) {
            when (animalFilter.value ?: AnimalFilter.ALL) {
                AnimalFilter.ALL -> animalRepository.getAll()
                AnimalFilter.DOG -> animalRepository.getDogs()
                AnimalFilter.CAT -> animalRepository.getCats()
            }.collect {
                _animalList.postValue(it)
            }
        }
    }

    fun selectFilter(filter: AnimalFilter) {
        _animalFilter.value = filter
        load()
    }

    private val _detailAnimal = MutableLiveData<Animal>()
    val detailAnimal: LiveData<Animal> get() = _detailAnimal

    fun getAnimalById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            animalRepository.getById(id).collect {
                _detailAnimal.postValue(it)
            }
        }
    }

    fun favorite(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            animalRepository.toggleFavorite(id).collect {
                val animal = it ?: return@collect
                val list = _animalList.value ?: return@collect
                _animalList.postValue(list.map { current ->
                    if (current.id == animal.id) animal else current
                })
                println(animal)
                println(_animalList.value?.firstOrNull())
                _detailAnimal.postValue(animal)
            }
        }
    }
}

enum class AnimalFilter {
    ALL, DOG, CAT
}