package com.example.cartoonapp

import com.airbnb.epoxy.EpoxyController
import com.example.cartoonapp.databinding.ModelCharacterDetailsDataPointBinding
import com.example.cartoonapp.databinding.ModelCharacterDetailsHeaderBinding
import com.example.cartoonapp.databinding.ModelHeaderDetailsImageBinding
import com.example.cartoonapp.epoxy.LoadingEpoxyModel
import com.example.cartoonapp.epoxy.ViewBindingKotlinModel
import com.example.cartoonapp.network.response.GetCharacterByIdResponse
import com.squareup.picasso.Picasso

class CharacterDetailsEpoxyController: EpoxyController() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var character: com.example.cartoonapp.domain.models.Character? = null
        set(value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    override fun buildModels() {

        if(isLoading) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        if (character==null) {

            return
        }

        //Header Model
        HeaderEpoxyModel(
            name = character!!.name,
            gender = character!!.gender,
            status = character!!.status
        ).id("header").addTo(this)

        //add image model
        ImageEpoxyModel(
            imageUrl = character!!.image
        ).id("image").addTo(this)

        //add the data points model(s)
        DataPointEpoxyModel(
            title = "Origin",
            description = character!!.origin.name
        ).id("data_point_1").addTo(this)

        DataPointEpoxyModel(
            title = "Species",
            description = character!!.species
        ).id("data_point_2").addTo(this)

    }

    data class HeaderEpoxyModel(
        val name: String,
        val gender: String,
        val status: String
    ): ViewBindingKotlinModel<ModelCharacterDetailsHeaderBinding>(R.layout.model_character_details_header) {

        override fun ModelCharacterDetailsHeaderBinding.bind() {
            nameTextView.text = name
            aliveTextView.text = status

            if (gender.equals("male",true)) {
                genderImageView.setImageResource(R.drawable.male)
            } else {
                genderImageView.setImageResource(R.drawable.female)
            }
        }
    }

    data class ImageEpoxyModel(
        val imageUrl: String
    ): ViewBindingKotlinModel<ModelHeaderDetailsImageBinding>(R.layout.model_header_details_image) {

        override fun ModelHeaderDetailsImageBinding.bind() {
            Picasso.get().load(imageUrl).into(headerImageView)
        }
    }

    data class DataPointEpoxyModel(
        val title: String,
        val description: String
    ): ViewBindingKotlinModel<ModelCharacterDetailsDataPointBinding>(R.layout.model_character_details_data_point) {

        override fun ModelCharacterDetailsDataPointBinding.bind() {
            labelTextView.text = title
            textView.text = description
        }
    }

}