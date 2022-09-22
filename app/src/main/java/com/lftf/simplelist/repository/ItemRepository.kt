package com.lftf.simplelist.repository

import android.content.ContentValues
import android.content.Context
import com.lftf.simplelist.data.DatabaseHelper
import com.lftf.simplelist.models.ItemModel

class ItemRepository(context: Context) {
    private val dbHelper = DatabaseHelper(context)
    private val DB_DEF = ItemModel.DatabaseDefinition
    private val DB_COLUMNS = ItemModel.DatabaseDefinition.Columns

    fun save(item: ItemModel): Int {
        val db = dbHelper.writableDatabase
        val itemValues = ContentValues()

        with(itemValues) {
            put(DB_COLUMNS.TITLE, item.title)
            put(DB_COLUMNS.QUANTITY, item.quantity)
            put(DB_COLUMNS.VALUE, item.value)
        }

        return db.insert(DB_DEF.TABLE_NAME, null, itemValues).toInt()
    }
}