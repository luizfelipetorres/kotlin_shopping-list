package com.lftf.simplelist.addItem

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.lftf.simplelist.R
import com.lftf.simplelist.data.DataManager
import com.lftf.simplelist.models.ItemModel

class addItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        val title = findViewById<EditText>(R.id.field_title)
        val value = findViewById<EditText>(R.id.field_value)
        val quantity = findViewById<EditText>(R.id.field_quantity)

        findViewById<Button>(R.id.button_save).setOnClickListener {
            val stringTitle = title.text.toString()
            val floatValue = value.text.toString().toFloat()
            val intQuantity: Int = when (quantity.text.toString()) {
                "" -> 1
                else -> quantity.text.toString().toInt()
            }
            ItemModel(stringTitle, quantity = intQuantity, value = floatValue).apply {
                DataManager.addItem(this)
                finish()
                Toast.makeText(this@addItemActivity, "Item salvo", Toast.LENGTH_LONG).show()
            }
        }
    }
}