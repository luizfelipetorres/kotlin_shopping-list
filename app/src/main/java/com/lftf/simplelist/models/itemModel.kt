package com.lftf.simplelist.models

data class itemModel(var title: String, var quantity: Int = 1, var value: Float) {

    fun getTotalValue(): Float = quantity * value
}