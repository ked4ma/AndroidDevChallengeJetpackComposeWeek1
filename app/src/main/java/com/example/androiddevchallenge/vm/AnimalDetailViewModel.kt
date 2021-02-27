/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
