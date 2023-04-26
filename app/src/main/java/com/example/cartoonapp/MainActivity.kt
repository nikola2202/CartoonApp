package com.example.cartoonapp

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cartoonapp.data.GetCharacterByIdResponse
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        val headerImageView = findViewById<ImageView>(R.id.headerImageView)
        val aliveTextView = findViewById<TextView>(R.id.aliveTextView)
        val originTextView = findViewById<TextView>(R.id.originTextView)
        val speciesTextView = findViewById<TextView>(R.id.speciesTextView)
        val genderImageView = findViewById<ImageView>(R.id.genderImageView)

        viewModel.refreshCharacter(54)
        viewModel.characterIdLiveData.observe(this) {response ->
            if (response==null) {
                Toast.makeText(
                    this@MainActivity,
                    "Unsuccessful network call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }

            nameTextView.text = response.name
            aliveTextView.text = response.status
            speciesTextView.text = response.species
            originTextView.text = response.origin.name

            if (response.gender.equals("male",true)) {
                genderImageView.setImageResource(R.drawable.male)
            } else {
                genderImageView.setImageResource(R.drawable.female)
            }

            Picasso.get()
                .load(response.image)
                .into(headerImageView)

        }


    }
}