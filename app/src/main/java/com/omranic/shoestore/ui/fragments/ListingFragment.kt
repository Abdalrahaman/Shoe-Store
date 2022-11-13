package com.omranic.shoestore.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.omranic.shoestore.R
import com.omranic.shoestore.databinding.FragmentListingBinding
import com.omranic.shoestore.databinding.ItemShoeBinding
import com.omranic.shoestore.viewmodels.ShoeViewModel

class ListingFragment : Fragment() {

    private lateinit var binding: FragmentListingBinding
    private lateinit var itemBinding: ItemShoeBinding

    private val shoeViewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_listing, container, false)
        binding.buttonAddShoe.setOnClickListener {
            findNavController().navigate(ListingFragmentDirections.actionListingFragmentToDetailFragment())
        }
        setupMenu()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shoeViewModel.shoes.observe(viewLifecycleOwner) { shoes ->
            for (shoe in shoes) {
                itemBinding = ItemShoeBinding.inflate(layoutInflater)
                itemBinding.shoe = shoe
                binding.linearLayoutShoes.addView(itemBinding.root)
            }
        }
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.overflow_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menuLogout -> {
                        findNavController().navigate(ListingFragmentDirections.actionListingFragmentToLoginFragment())
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}