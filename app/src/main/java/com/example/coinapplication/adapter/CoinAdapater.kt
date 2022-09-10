package com.example.coinapplication.adapter

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.coinapplication.Dto.CoinDetails
import com.example.coinapplication.R
import com.example.coinapplication.databinding.CoinItemsBinding

class CoinAdapater(val context: Context): RecyclerView.Adapter<CoinAdapater.MyViewholder>() {



    inner class MyViewholder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val img: ImageView = itemView.findViewById(R.id.coinImage)
        val name = itemView.findViewById<TextView>(R.id.coinName)
    }

        private val differCallback = object : DiffUtil.ItemCallback<CoinDetails>() {
            override fun areItemsTheSame(oldItem: CoinDetails, newItem: CoinDetails): Boolean {
                return oldItem.isCoin == newItem.isCoin
            }

            override fun areContentsTheSame(oldItem: CoinDetails, newItem: CoinDetails): Boolean {
                return oldItem == newItem
            }
        }

        val differ = AsyncListDiffer(this@CoinAdapater,differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {

        return MyViewholder(LayoutInflater.from(parent.context).inflate(R.layout.coin_items,parent, false))

    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        val coin = differ.currentList[position]
        holder.name.text = coin.name
        Glide.with(holder.itemView).load(coin.pictures?.front?.url).into(holder.img)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}