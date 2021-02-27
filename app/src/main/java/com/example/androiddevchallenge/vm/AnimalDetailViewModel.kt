package com.example.androiddevchallenge.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.model.Animal
import com.example.androiddevchallenge.data.repository.AnimalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AnimalDetailViewModel(private val animalId: Long) : ViewModel() {
    private val animalRepository = AnimalRepository

    private val _detailAnimal = MutableLiveData<Animal>()
    val detailAnimal: LiveData<Animal> get() = _detailAnimal

    init {
        viewModelScope.launch(Dispatchers.IO) {
            animalRepository.getById(animalId).collect {
                _detailAnimal.postValue(it)
            }
        }
    }

    fun favorite(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            animalRepository.toggleFavorite(id).collect {
                val animal = it ?: return@collect
                _detailAnimal.postValue(animal)
            }
        }
    }

    class Factory(private val animalId: Long) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return AnimalDetailViewModel(animalId) as T
        }
    }
}