package com.example.androiddevchallenge.data.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.IntRange
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class Animal(
    val id: Long,
    val type: Type,
    val name: String,
    @DrawableRes val imageRes: Int,
    @IntRange(from = 0, to = 1200) val ageInMonth: Int,
    val breed: String,
    val gender: Gender,
    val info: String,
    val favorite: Boolean = false
) : Parcelable {
    enum class Type {
        CAT, DOG
    }

    enum class Gender {
        MALE, FEMALE
    }
}