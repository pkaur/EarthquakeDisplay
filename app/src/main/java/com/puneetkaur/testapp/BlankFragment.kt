package com.puneetkaur.testapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.puneetkaur.testapp.databinding.FragmentBlankBinding
import com.puneetkaur.testapp.model.Earthquake

class BlankFragment : Fragment() {
    private lateinit var binding: FragmentBlankBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentBlankBinding.inflate(inflater, container, false)

        // Log details when receiving the argument
        Log.d("BlankFragment", "Clicked Item: ${arguments?.getParcelable<Earthquake>("clickedItem")}")

        val clickedItem = arguments?.getParcelable<Earthquake>("clickedItem")

        updateUI(clickedItem)
        return binding.root
    }

    private fun updateUI(clickedItem: Earthquake?) {

        binding.textview.text = clickedItem?.eqid.toString()


    }
}