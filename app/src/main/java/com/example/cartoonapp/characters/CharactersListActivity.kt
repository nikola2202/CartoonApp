package com.example.cartoonapp.characters

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.cartoonapp.Constants
import com.example.cartoonapp.CharacterDetailsActivity
import com.example.cartoonapp.R

class CharactersListActivity: AppCompatActivity() {

    private val epoxyController = CharacterListPagingEpoxyController(::onCharacterSelected)

    private val viewModel: CharactersViewModel by lazy {
        ViewModelProvider(this).get(CharactersViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        viewModel.charactersPagedListLiveData.observe(this) {pagedList->
            epoxyController.submitList(pagedList)
        }

        findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView).setController(epoxyController)

    }

    private fun onCharacterSelected(characterId: Int) {

        val intent = Intent(this,CharacterDetailsActivity::class.java)
        intent.putExtra(Constants.INTENT_EXTRA_CHARACTER_ID,characterId)
        startActivity(intent)

    }

}