package com.lftf.simplelist.addItem

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.lftf.simplelist.R
import com.lftf.simplelist.data.DataManager
import com.lftf.simplelist.models.itemModel

class addItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        val title = findViewById<EditText>(R.id.field_title)
        val value = findViewById<EditText>(R.id.field_value)
        val quantity = findViewById<EditText>(R.id.field_quantity)

        findViewById<Button>(R.id.button_save).setOnClickListener(View.OnClickListener {
            itemModel(
                title.text.toString(),
                quantity = quantity.text.toString().toInt(),
                value = value.text.toString().toFloat()
            ).apply {
                DataManager.addItem(this)
                finish()
                Toast.makeText(this@addItemActivity,"Item salvo", Toast.LENGTH_LONG).show()
            }
        })
    }
}