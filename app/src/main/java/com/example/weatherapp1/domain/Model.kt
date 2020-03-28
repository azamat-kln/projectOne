package com.example.weatherapp1.domain

import android.os.Parcelable
import com.example.weatherapp1.database.EmploymentDb
import com.example.weatherapp1.database.MockyEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Model(
    val email: String,
    val employment: EmploymentModel,
    val first_name: String,
    val gender: String,
    val id: Int,
    val ip_address: String,
    val last_name: String,
    val photo: String
) : Parcelable

@Parcelize
data class EmploymentModel(
    val name: String,
    val position: String
) : Parcelable

fun EmploymentDb.asEmploymentModel(): EmploymentModel {
    return EmploymentModel(name = this.name, position = this.position)
}

fun List<MockyEntity>.asDomainModel(): List<Model> {
    return this.map {
        Model(
            email = it.email,
            employment = it.employment.asEmploymentModel(),
            first_name = it.first_name,
            gender = it.gender,
            id = it.id,
            ip_address = it.ip_address,
            last_name = it.last_name,
            photo = it.photo
        )
    }
}