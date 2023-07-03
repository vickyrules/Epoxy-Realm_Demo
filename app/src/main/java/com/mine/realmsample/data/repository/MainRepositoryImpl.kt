package com.mine.realmsample.data.repository

import android.util.Log
import androidx.appcompat.view.menu.MenuView.ItemView
import com.mine.realmsample.data.model.ItemNew
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor( val realm: Realm) : MainRepository {


    override fun getData(): Flow<List<ItemNew>> {
        return realm.query<ItemNew>().asFlow().map { it.list }
    }

    override fun filterData(name: String): Flow<List<ItemNew>> {
        return realm.query<ItemNew>().asFlow().map { it.list }
    }

    override suspend fun insertItem(item: ItemNew) {
        realm.write { copyToRealm(item) }
    }

    override suspend fun updateItem(item: ItemNew) {
        realm.write {
            val querieItem = query<ItemNew>(query = "id ==$0 ", item._objectID).first().find()
            querieItem?.name = item.name
        }
    }

    override suspend fun deleteItem(objectId: ObjectId) {
        realm.write {
            val item = query<ItemNew>(query = "id = $0", objectId).first().find()
            try {

            } catch (exception: Exception) {
                Log.d("MainRepositoryImpl", "${exception.message}")
            }

        }

    }


}