package com.example.weatherapp1.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.weatherapp1.R
import com.example.weatherapp1.databinding.FragmentDetailBinding
import com.example.weatherapp1.domain.Model
import com.example.weatherapp1.network.Mocky

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        val selectedMockyProperties: Model = DetailFragmentArgs.fromBundle(arguments!!).foo

        val viewModelFactory = DetailViewModelFactory(selectedMockyProperties)

        val detailViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DetailFragmentViewModel::class.java)

        binding.apply {
            viewmodel = detailViewModel
            lifecycleOwner = this@DetailFragment
        }

        return binding.root
    }

}
