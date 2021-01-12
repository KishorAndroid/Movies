package com.kishordahiwadkar.movies.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kishordahiwadkar.movies.R
import com.kishordahiwadkar.movies.ViewModelFactory
import com.kishordahiwadkar.movies.network.service
import com.kishordahiwadkar.movies.repository.MoviesRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private val adapter = MoviesAdapter()

    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this, ViewModelFactory(MoviesRepository(service = service))).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var list = view.findViewById<RecyclerView>(R.id.list)
        list.layoutManager = GridLayoutManager(activity, 2)
        list.adapter = adapter

        getMovies("")
    }

    private fun getMovies(query: String) {
        job?.cancel()
        job = lifecycleScope.launch {
            homeViewModel.getMovies(query).collectLatest {
                adapter.submitData(it)
            }
        }
    }
}