package com.example.bitfit2

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfit2.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater

class LogFragment : Fragment() {
    private val foodList = mutableListOf<DisplayFood>()
    private lateinit var foodRecyclerView: RecyclerView
    private lateinit var foodAdapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_log, container, false)
        val layoutManager = LinearLayoutManager(context)
        foodRecyclerView = view.findViewById(R.id.food_recycler_view)
        foodRecyclerView.layoutManager = layoutManager
        foodRecyclerView.setHasFixedSize(true)
        foodAdapter = FoodAdapter(view.context, foodList)
        foodRecyclerView.adapter = foodAdapter

        return view
    }

    private fun fetchFoodList() {
        lifecycleScope.launch {
            (activity?.application as FoodApplication).db.foodDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayFood(
                        entity.foodName,
                        entity.caloriesNumber,
                        entity.caloriesText
                    )
                }.also { mappedList ->
                    foodList.addAll(mappedList)
                    foodAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the new method within onViewCreated
        fetchFoodList()
    }

    companion object {
        fun newInstance(): DashboardFragment {
            return DashboardFragment()
        }
    }
}