package com.lftf.simplelist.data

import android.content.ClipData
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.lftf.simplelist.models.ItemModel

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_SHOPPING_LIST)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val DB_NAME = "shopping_list.db"
        private const val DB_VERSION = 1
        private val DB_DEF = ItemModel.DatabaseDefinition
        private val DB_COLUMNS = ItemModel.DatabaseDefinition.Columns

        private const val CREATE_TABLE_SHOPPING_LIST =
            "CREATE TABLE ${DB_DEF.TABLE_NAME} (" +
                    "${DB_COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${DB_COLUMNS.TITLE} TEXT, " +
                    "${DB_COLUMNS.VALUE} REAL, " +
                    "${DB_COLUMNS.QUANTITY} INTEGER)"
    }
}