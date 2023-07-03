package com.mine.realmsample.main

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.google.android.material.button.MaterialButton
import com.mine.realmsample.R
import com.mine.realmsample.data.model.ItemNew
import com.mine.realmsample.data.model.fakeData
import com.mine.realmsample.epoxy.ListItemModel_
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var epoxyRecyclerView: EpoxyRecyclerView
    private lateinit var epoxyController: EpoxyController

    private lateinit var addButton: MaterialButton
    private lateinit var updateButton: MaterialButton
    private lateinit var deleteButton: MaterialButton
    private lateinit var filterButton: MaterialButton

    private lateinit var idInputTextEditText: EditText
    private lateinit var nameInputEditText: EditText


    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.getAllData()

        setContentView(R.layout.activity_main)
        epoxyRecyclerView = findViewById(R.id.recyclerView)

        addButton = findViewById(R.id.addBtn)
        updateButton = findViewById(R.id.updateBtn)
        deleteButton = findViewById(R.id.deleteBtn)
        filterButton = findViewById(R.id.filterBtn)

        nameInputEditText = findViewById(R.id.nameTextField)
        idInputTextEditText = findViewById(R.id.idTextField)




        mainViewModel.publicData.observe(this@MainActivity) { newData ->
            callEpoxy(newData)

        }
        nameInputEditText.doOnTextChanged { text, start, before, count ->
            addButton.isEnabled = !text.isNullOrBlank()
            deleteButton.isEnabled = !text.isNullOrBlank()
            updateButton.isEnabled = !text.isNullOrBlank()
            filterButton.isEnabled = !text.isNullOrBlank()


        }

        idInputTextEditText.doOnTextChanged { text, start, before, count ->

            deleteButton.isEnabled = !text.isNullOrBlank()
            updateButton.isEnabled = !text.isNullOrBlank()
        }

        addButton.setOnClickListener {
            mainViewModel.insertItem(name = nameInputEditText.text.toString())
            nameInputEditText.setText("")
        }
        deleteButton.setOnClickListener {
            mainViewModel.deleteItem(objectId = idInputTextEditText.text.toString())
            idInputTextEditText.setText("")
        }

        filterButton.setOnClickListener {
            mainViewModel.filterData(nameInputEditText.text.toString())
        }

    }

    private fun callEpoxy(newData: List<ItemNew>) {
        epoxyController = object : EpoxyController() {
            override fun buildModels() {

                mainViewModel._data.value?.forEachIndexed { index, item ->
                    ListItemModel_()
                        .id(index)
                        .name(item.name)
                        .objID(item._objectID.toHexString())
                        .time(
                            String.format(
                                "%02d",
                                Random.nextInt(0, 23)
                            ) + ":" + String.format("%02d", Random.nextInt(0, 59))
                        )
                        .addTo(this)
                }
            }
        }

        epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }
}
