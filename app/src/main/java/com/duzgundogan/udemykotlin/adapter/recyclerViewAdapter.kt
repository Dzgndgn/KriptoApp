package com.duzgundogan.udemykotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.duzgundogan.udemykotlin.R
import com.duzgundogan.udemykotlin.databinding.RawLayoutBinding
import com.duzgundogan.udemykotlin.model.CryptoModel

class recyclerViewAdapter(
    private val cryptoList: ArrayList<CryptoModel>,
    private val listener: Listener
) : RecyclerView.Adapter<recyclerViewAdapter.RowHolder>() {

    interface Listener {
        fun onItemClick(cryptoModel: CryptoModel)
    }

    // Renk dizisi
    private val colors :Array<String> = arrayOf("#F5F5F5","#E0E0E0","#BDBDBD","#FFFFFF","#D6EAF8")
    //"#2E2E2E", "#242424", "#3A3A3A", "#1F1F1F", "#353535"

   /* class RowHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val cryptoName: TextView = view.findViewById(R.id.cryptoName)
        private val cryptoSymbol: TextView = view.findViewById(R.id.cryptoAbbreviation)
        private val cryptoPrice: TextView = view.findViewById(R.id.cryptoPrice)

        fun bind(
            cryptoModel: CryptoModel,
            colors: Array<String>,
            position: Int,
            listener: Listener
        ) {
            // Arka plan rengini belirle
            itemView.setBackgroundColor(Color.parseColor(colors[position % 5]))
            // Verileri bağla
            cryptoName.text = cryptoModel.name
            cryptoSymbol.text = cryptoModel.symbol
            cryptoPrice.text = "${cryptoModel.quote.usd.price} USD"

            // Tıklama dinleyicisi


        }
    }*/
    class RowHolder(val binding :RawLayoutBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val binding = RawLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder(binding)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
       // holder.bind(cryptoList[position], colors, position, listener)
        holder.itemView.setBackgroundColor(Color.parseColor(colors[position %5]))
        holder.itemView.setOnClickListener {
            listener.onItemClick(cryptoList[position])
        }
        holder.binding.cryptoName.text =cryptoList.get(position).name
        holder.binding.cryptoPrice.text ="${cryptoList.get(position).quote.usd.price} USD"
        holder.binding.cryptoAbbreviation.text=cryptoList.get(position).symbol



    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }
}
