package com.lftf.simplelist.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.lftf.simplelist.data.DatabaseHelper
import com.lftf.simplelist.models.ItemModel

class ItemRepository(context: Context) {
    /**
     * Variáveis auxiliares
     */
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

    fun getItens(): ArrayList<ItemModel> {
        val db = dbHelper.readableDatabase

        val cursor = db.query(
            DB_DEF.TABLE_NAME, null, null, null, null, null, null, null
        )

        var itens = ArrayList<ItemModel>();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                var item = ItemModel(
                    id = cursor.getInt(cursor.getColumnIndex(DB_COLUMNS.ID).toInt()),
                    title = cursor.getString(cursor.getColumnIndex(DB_COLUMNS.TITLE).toInt()),
                    quantity = cursor.getInt(cursor.getColumnIndex(DB_COLUMNS.QUANTITY).toInt()),
                    value = cursor.getFloat(cursor.getColumnIndex(DB_COLUMNS.VALUE).toInt())
                )
                itens.add(item)
            }
        }
        return itens
    }

    fun getItem(id: Int): ItemModel{
        val db = dbHelper.readableDatabase

        val list = getItens()
        return list.get(id)
    }

    fun delete(id: Int): Int {
        val db = dbHelper.writableDatabase

        return db.delete(DB_DEF.TABLE_NAME, "${DB_COLUMNS.ID} = ?", arrayOf(id.toString()))
    }
}