package com.fthcesur.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_food.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val foodAdapter=FoodAdapter()
    private val service = RetrofitClient.getUniService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        GlobalScope.launch(Dispatchers.IO) {
            val result = service.getFoodList().data
            val foodItems = Gson().fromJson(result, FoodItems::class.java)
            Log.e("result", foodItems.items.toString())
            withContext(Dispatchers.Main){
                foodAdapter.setNewItems(foodItems.items.orEmpty())
            }
            foodAdapter.setNewItems(foodItems.items.orEmpty())
        }
    }

    private fun initUI(){
        foodRecyclerView.apply {
            adapter=foodAdapter
            layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        }
    }

    override fun getMaxNumPictureInPictureActions(): Int {
        return super.getMaxNumPictureInPictureActions()
    }
}
