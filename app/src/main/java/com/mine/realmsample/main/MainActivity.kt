package com.mine.realmsample.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.mine.realmsample.R
import com.mine.realmsample.data.model.Item
import com.mine.realmsample.data.model.fakeData
import com.mine.realmsample.epoxy.ListItemModel_

class MainActivity : AppCompatActivity() {


    private lateinit var epoxyController: EpoxyController
    private lateinit var epoxyRecyclerView: EpoxyRecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        epoxyRecyclerView = findViewById<EpoxyRecyclerView>(R.id.recyclerView)


        epoxyController = object : EpoxyController() {
            override fun buildModels() {

                fakeData.forEachIndexed() { index, item ->
                    ListItemModel_()
                        .id(index)
                        .name(item.name)
                        .objID(index.toString())
                        .time(item.createdAt).addTo(this)
                }
            }
        }

        epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }
}

