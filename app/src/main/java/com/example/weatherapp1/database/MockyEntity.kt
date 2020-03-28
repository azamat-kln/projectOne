package com.example.weatherapp1.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.weatherapp1.network.Employment
import com.example.weatherapp1.network.Mocky

@Entity
data class MockyEntity(
    @PrimaryKey val email: String,
    @Embedded
    val employment: EmploymentDb,
    val first_name: String,
    val gender: String,
    val id: Int,
    val ip_address: String,
    val last_name: String,
    val photo: String
)

data class EmploymentDb(
    val name: String,
    val position: String
)

fun Employment.toDbEmployment(): EmploymentDb {
    return EmploymentDb(name = name, position = position)
}

fun List<Mocky>.asDatabaseModel(): List<MockyEntity> {
    return this.map {
        MockyEntity(
            email = it.email,
            employment = it.employment.toDbEmployment(),
            first_name = it.first_name,
            gender = it.gender,
            id = it.id,
            ip_address = it.ip_address,
            last_name = it.last_name,
            photo = it.photo
        )
    }
}