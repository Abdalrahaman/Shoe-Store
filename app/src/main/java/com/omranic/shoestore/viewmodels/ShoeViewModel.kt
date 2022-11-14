package com.omranic.shoestore.viewmodels

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omranic.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    val shoeName = MutableLiveData<String>()

    val shoeSize = MutableLiveData<String>()

    val company = MutableLiveData<String>()

    val description = MutableLiveData<String>()

    private val shoeList = mutableListOf<Shoe>()

    private val _shoes = MutableLiveData<List<Shoe>>()
    val shoes: LiveData<List<Shoe>>
        get() = _shoes

    fun addShoe() {
        shoeList.add(
            Shoe(
                shoeName.value.toString().trim(),
                shoeSize.value.toString().toDouble(),
                company.value.toString().trim(),
                description.value.toString().trim()
            )
        )
        _shoes.value = shoeList
        clearShoeInputs()
    }

    private fun clearShoeInputs() {
        shoeName.value = ""
        shoeSize.value = ""
        company.value = ""
        description.value = ""
    }

    fun isNameEmpty(): Boolean {
        return shoeName.value.isNullOrEmpty()
    }

    fun isCompanyEmpty(): Boolean {
        return company.value.isNullOrEmpty()
    }

    fun isSizeEmptyOrNotDigits(): Boolean {
        return shoeSize.value.isNullOrEmpty() || !shoeSize.value!!.isDigitsOnly()
    }

    fun isDescriptionEmpty(): Boolean {
        return description.value.isNullOrEmpty()
    }
}