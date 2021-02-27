package com.example.androiddevchallenge.data.repository

import com.example.androiddevchallenge.data.dao.AnimalDao
import com.example.androiddevchallenge.data.model.Animal

class AnimalRepository {
    // use dummy dao
    private val dao = AnimalDao()

    fun getAll() = dao.getAll()
    fun getDogs() = dao.getByType(Animal.Type.DOG)
    fun getCats() = dao.getByType(Animal.Type.CAT)

    fun getById(id: Long) = dao.getById(id)
}