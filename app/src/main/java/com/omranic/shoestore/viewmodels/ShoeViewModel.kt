package com.omranic.shoestore.viewmodels

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omranic.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    private val _name = MutableLiveData<String>()

    private val _size = MutableLiveData<String>()

    private val _company = MutableLiveData<String>()

    private val _description = MutableLiveData<String>()

    private val shoeList = mutableListOf<Shoe>()

    private val _shoes = MutableLiveData<List<Shoe>>()
    val shoes: LiveData<List<Shoe>>
        get() = _shoes

    fun addShoe() {
        shoeList.add(
            Shoe(
                _name.value.toString().trim(),
                _size.value.toString().toDouble(),
                _company.value.toString().trim(),
                _description.value.toString().trim()
            )
        )
        _shoes.value = shoeList
    }

    fun onShoeNameSelected(name: String) {
        _name.value = name
    }

    fun onShoeCompanySelected(company: String) {
        _company.value = company
    }

    fun onShoeSizeSelected(size: String) {
        _size.value = size
    }

    fun onShoeDescriptionSelected(description: String) {
        _description.value = description
    }

    fun isNameEmpty(): Boolean {
        return _name.value.isNullOrEmpty()
    }

    fun isCompanyEmpty(): Boolean {
        return _company.value.isNullOrEmpty()
    }

    fun isSizeEmptyOrNotDigits(): Boolean {
        return _size.value.isNullOrEmpty() || !_size.value!!.isDigitsOnly()
    }

    fun isDescriptionEmpty(): Boolean {
        return _description.value.isNullOrEmpty()
    }
}