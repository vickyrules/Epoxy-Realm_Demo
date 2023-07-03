package com.mine.realmsample.data.model

import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


open class ItemNew: RealmObject {
    @PrimaryKey
    var _objectID :ObjectId = ObjectId.invoke()
    var name:String = ""
    var createdAt:RealmInstant = RealmInstant.now()
    @Index
    var indexNo = 0
}