package com.mine.realmsample.main

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mine.realmsample.data.model.ItemNew
import com.mine.realmsample.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    val _data = MutableLiveData<List<ItemNew>>()
    val publicData: LiveData<List<ItemNew>>
        get() = _data

    val filtered = mutableStateOf(false)


    init {
        getAllData()
    }

    fun getAllData() {
        viewModelScope.launch {
            val data = mainRepository.getData().collect {
                _data.value = (it)
            }
        }
    }


    fun insertItem(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (name.isNotBlank()) {
                mainRepository.insertItem(
                    item = ItemNew()
                )
            }
        }
    }

    fun updateItem(objectId:String,) {
        viewModelScope.launch(Dispatchers.IO) {
            if (objectId.isNotEmpty()) {
                mainRepository.updateItem(item = ItemNew().apply {
                    _objectID = ObjectId(hexString = objectId)

                })
            }
        }
    }


    fun deleteItem(objectId: String) {
        viewModelScope.launch {
            if (objectId.isNotEmpty()) {
                mainRepository.deleteItem(onjectId = ObjectId(hexString = objectId))
            }
        }
    }

    fun filterData(name:String) {
        Log.d("FilterCall", name)
        viewModelScope.launch(Dispatchers.IO) {
            if (filtered.value) {
                mainRepository.getData().collect {
                    filtered.value = false
                    _data.value = it
                }
            } else {
                mainRepository.filterData(name = name).collect {
                    filtered.value = true
                    _data.value = it
                }
            }
        }
    }

}