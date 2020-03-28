package com.example.weatherapp1.repository


import androidx.lifecycle.Transformations
import com.example.weatherapp1.database.MockyDao
import com.example.weatherapp1.database.MyDatabase
import com.example.weatherapp1.database.asDatabaseModel
import com.example.weatherapp1.domain.asDomainModel
import com.example.weatherapp1.network.Mocky
import com.example.weatherapp1.network.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(database: MyDatabase) {

    private val dao: MockyDao = database.roomDao

    // observe changed database
    val livedataMocky = Transformations.map(dao.getEntities()) {
        it.asDomainModel()
    }

    suspend fun getMockyData() {
        withContext(Dispatchers.IO) {
            // get data from web service/api
            val responce: List<Mocky> = UserApi.retrofitService.getMocky().await()
            // insert the data to database right away
            dao.insert(responce.asDatabaseModel())
        }
    }

}