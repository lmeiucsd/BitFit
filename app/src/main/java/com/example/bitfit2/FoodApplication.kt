package com.example.bitfit2

import android.app.Application
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

class FoodApplication: Application() {
    val db by lazy {AppDataBase.getInstance(this)}
}