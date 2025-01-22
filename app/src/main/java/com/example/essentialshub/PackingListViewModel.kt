package com.example.essentialshub
import androidx.lifecycle.ViewModel

class PackingListViewModel : ViewModel() {
    val weekendTripItems = mutableListOf(
        PackingItem("Toothbrush"),
        PackingItem("Shampoo"),
        PackingItem("Clothes"),
        PackingItem("Shoes"),
        PackingItem("Sunscreen"),
        PackingItem("Towel"),
        PackingItem("Passport"),
        PackingItem("Charger"),
        PackingItem("Snacks"),
        PackingItem("Water Bottle")
    )

    val sunnyWeekItems = mutableListOf(
        PackingItem("Sunglasses"),
        PackingItem("Hat"),
        PackingItem("Sunscreen"),
        PackingItem("Swimsuit"),
        PackingItem("Towel"),
        PackingItem("Flip Flops"),
        PackingItem("Camera"),
        PackingItem("Beach Bag")
    )

    val workTripItems = mutableListOf(
        PackingItem("Laptop"),
        PackingItem("Charger"),
        PackingItem("Notepad"),
        PackingItem("Pens"),
        PackingItem("Business Cards"),
        PackingItem("Suit"),
        PackingItem("Dress Shoes"),
        PackingItem("Power Bank"),
        PackingItem("Headphones")
    )

    fun getItemsForListType(listType: String): MutableList<PackingItem> {
        return when (listType) {
            "Weekend Trip" -> weekendTripItems
            "Sunny Week" -> sunnyWeekItems
            "Work Trip" -> workTripItems
            else -> mutableListOf()
        }
    }
}