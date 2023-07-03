package com.mine.realmsample.data.repository

import com.mine.realmsample.data.model.ItemNew
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface MainRepository {
    fun getData(): Flow<List<ItemNew>>
    fun filterData(name: String): Flow<List<ItemNew>>

    suspend fun insertItem(item: ItemNew)
    suspend fun updateItem(item: ItemNew)
    suspend fun deleteItem(onjectId: ObjectId)
}