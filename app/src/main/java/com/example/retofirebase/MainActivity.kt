package com.example.retofirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retofirebase.databinding.ActivityMainBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.json.JSONArray


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = Firebase.database
        val myRef = database.reference

        binding.button.setOnClickListener {
           /* myRef.child("Peliculas").child("" + binding.etid.text.toString()).setValue(
                Peliculas(
                    "" + binding.etid.text.toString(),
                    "" + binding.etTitle.text.toString(),
                    "" + binding.etYear.text.toString(),
                    "" + binding.etIMD.text.toString(),
                    "" + binding.etType.text.toString(),
                    "" + binding.etPoster.text.toString(),
                    "" + binding.etCountry.text.toString(),
                    "" + binding.etGenre.text.toString()
                )
            )*/
            myRef.child("Peliculas").get().addOnSuccessListener { response ->
                val resmap = response.value as ArrayList<Map <String, String>>
                Log.d("firebaseResponse", resmap.toString())
                val jsonarray = JSONArray(resmap)
                resmap.forEach{ pelicula ->
                    if(!pelicula.isNullOrEmpty()){
                        Log.d("firebaseResponse", "titulo : ${pelicula["titulo"]}")
                        binding.rvPeliculas.adapter = MainAdapter(jsonarray)
                    }
                    binding.rvPeliculas.adapter = MainAdapter(jsonarray)
                }}.addOnFailureListener{
                Log.e("firebaseResponse", "Error getting data", it)
            }
        }


    }
}