package com.gcr.mistragos.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gcr.mistragos.R
import com.gcr.mistragos.base.BaseViewHolder
import com.gcr.mistragos.domain.data.model.Drink
import kotlinx.android.synthetic.main.drink_item.view.*

class RvAdapter(val drinkListener: drinkOnclickListener,val context: Context, val drinkList: List<Drink>) : RecyclerView.Adapter<BaseViewHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.drink_item,parent,false))
    }

    override fun getItemCount(): Int {
        return drinkList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder-> holder.bind(drinkList[position],position)
        }
    }

    inner class MainViewHolder(itemView: View): BaseViewHolder<Drink>(itemView){
        override fun bind(item: Drink, position: Int) {
            Glide.with(context).load(item.image).into(itemView.imgDrink)
            itemView.tvNameDrink.text = item.name
            itemView.tvDescriptionDrink.text = item.description

            itemView.itemDrink.setOnClickListener {
                drinkListener.clickListener(item,position)
            }
        }
    }

    interface drinkOnclickListener{
        fun clickListener(drink: Drink,position: Int)
    }
}