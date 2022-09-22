package com.lftf.simplelist.models

import java.net.IDN

data class ItemModel(var id: Int = 0,var title: String, var quantity: Int = 1, var value: Float = 0f) {

    fun getTotalValue(): Float = value * quantity

    object DatabaseDefinition{
        const val TABLE_NAME = "tb_item"

        object Columns{
            const val ID = "id"
            const val TITLE = "title"
            const val QUANTITY = "quantity"
            const val VALUE = "value"
        }
    }
}