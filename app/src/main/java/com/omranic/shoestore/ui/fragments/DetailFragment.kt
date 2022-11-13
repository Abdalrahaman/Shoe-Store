package com.omranic.shoestore.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.omranic.shoestore.R
import com.omranic.shoestore.databinding.FragmentDetailBinding
import com.omranic.shoestore.viewmodels.ShoeViewModel

class DetailFragment : Fragment() {

    private val shoeViewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.buttonCancel.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.buttonSave.setOnClickListener {
            shoeViewModel.onShoeNameSelected(binding.editTextName.text.toString())
            shoeViewModel.onShoeCompanySelected(binding.editTextCompany.text.toString())
            shoeViewModel.onShoeSizeSelected(binding.editTextSize.text.toString())
            shoeViewModel.onShoeDescriptionSelected(binding.editTextDescription.text.toString())

            when {
                shoeViewModel.isNameEmpty() -> Toast.makeText(
                    requireContext(),
                    getString(R.string.error_empty_name),
                    Toast.LENGTH_SHORT
                ).show()
                shoeViewModel.isCompanyEmpty() -> Toast.makeText(
                    requireContext(),
                    getString(R.string.error_empty_company),
                    Toast.LENGTH_SHORT
                ).show()
                shoeViewModel.isSizeEmptyOrNotDigits() -> Toast.makeText(
                    requireContext(),
                    getString(R.string.error_empty_or_not_digits_size),
                    Toast.LENGTH_SHORT
                ).show()
                shoeViewModel.isDescriptionEmpty() -> Toast.makeText(
                    requireContext(),
                    getString(R.string.error_empty_description),
                    Toast.LENGTH_SHORT
                ).show()
                else -> {
                    shoeViewModel.addShoe()
                    findNavController().popBackStack()
                }
            }
        }
        return binding.root
    }
}