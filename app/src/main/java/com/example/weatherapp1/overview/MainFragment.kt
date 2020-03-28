package com.example.weatherapp1.overview


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.weatherapp1.ClickListener
import com.example.weatherapp1.MyAdapter
import com.example.weatherapp1.R
import com.example.weatherapp1.databinding.FragmentBlankBinding

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private lateinit var binding: FragmentBlankBinding

    //private lateinit var fragmentViewModel: BlankFragmentViewModel

    private val fragmentViewModel: BlankFragmentViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProviders.of(this, MainViewModelFactory(activity.application))
            .get(BlankFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_blank, container, false)

        binding.apply {
            viewmodel = fragmentViewModel
            lifecycleOwner = this@MainFragment
        }

        binding.recyclerView.adapter = MyAdapter(ClickListener {
            fragmentViewModel.onTextClicked(it)
        })

        fragmentViewModel.selectedModel.observe(viewLifecycleOwner, Observer {
            it?.let {
                val action = MainFragmentDirections.actionBlankFragmentToDetailFragment(it)
                this.findNavController().navigate(action)

                fragmentViewModel.completeNavigation()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.gendermenu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.all_gender -> fragmentViewModel.onAllMenuSelected()
            R.id.male_menu -> fragmentViewModel.onMaleMenuSelected()
            R.id.female_menu -> fragmentViewModel.onFemaleMenuSelected()
        }
        return true
    }

}
