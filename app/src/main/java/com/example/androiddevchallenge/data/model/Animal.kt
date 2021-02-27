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
