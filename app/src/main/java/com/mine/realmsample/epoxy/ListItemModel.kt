package com.mine.realmsample.epoxy

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.mine.realmsample.R

@EpoxyModelClass(layout = R.layout.content_item)



abstract class ListItemModel: EpoxyModelWithHolder<ListItemModel.ListItemHolder>() {
    @EpoxyAttribute
    lateinit var name:String

    @EpoxyAttribute
    lateinit var objID:String

    @EpoxyAttribute
    lateinit var time: String



    override fun getDefaultLayout(): Int {
       return R.layout.content_item
    }

    override fun bind(holder: ListItemHolder) {
     holder.nameTextView.text = name
     holder.objIdTextView.text = objID
     holder.timeTextView.text = time

    }

    class ListItemHolder: EpoxyHolder() {
        lateinit var nameTextView: TextView
        lateinit var objIdTextView: TextView
        lateinit var timeTextView: TextView

        override fun bindView(itemView: View) {
            nameTextView = itemView.findViewById(R.id.nameTextView)
            objIdTextView = itemView.findViewById(R.id.objIDTextView)
            timeTextView = itemView.findViewById(R.id.timeTextView)
        }
    }

}