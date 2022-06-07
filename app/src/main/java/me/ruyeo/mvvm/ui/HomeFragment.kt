package me.ruyeo.mvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.ruyeo.mvvm.R
import me.ruyeo.mvvm.data.local.entity.News
import me.ruyeo.mvvm.utils.UiStateList
import me.ruyeo.mvvm.utils.UiStateObject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val news = News(id = 1,title = "", description = "", image = "")
        viewModel.addNews(news)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()


    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.newsState.collect {
                    when(it){
                        is UiStateObject.LOADING -> {
                            //loading progress show
                        }
                        is UiStateObject.SUCCESS -> {
                            //adapterga listni bervoramiz
                            Toast.makeText(requireContext(), it.data.description, Toast.LENGTH_SHORT).show()
                        }
                        is UiStateObject.ERROR -> {
                            //show error like
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}