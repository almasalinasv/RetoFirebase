package com.example.retofirebase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retofirebase.databinding.ShowDataBinding
import org.json.JSONArray
import org.json.JSONObject

class MainAdapter(private val peliculas: JSONArray): RecyclerView.Adapter<MainAdapter.MainHolder>()   {
    class MainHolder(val binding: ShowDataBinding):RecyclerView.ViewHolder(binding.root) {
        fun render(peliculas : JSONObject){
            binding.tvTitle.setText(peliculas.getString("titulo"))
            binding.tvYear.setText(peliculas.getString("year"))
            binding.tvIMD.setText(peliculas.getString("imd"))
            binding.tvType.setText(peliculas.getString("type"))
            binding.tvPoster.setText(peliculas.getString("poster"))
            binding.tvCountry.setText(peliculas.getString("country"))
            binding.tvGenre.setText(peliculas.getString("genre"))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = ShowDataBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        if(peliculas[position].toString()!="null"){
            holder.render(peliculas[position] as JSONObject)
        }
    }

    override fun getItemCount():Int = peliculas.length()

}