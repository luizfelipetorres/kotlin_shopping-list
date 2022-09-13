package com.lftf.simplelist.models

data class ItemModel(var title: String, var quantity: Int = 1, var value: Float) {

    fun getTotalValue(): Float = value * quantity
}