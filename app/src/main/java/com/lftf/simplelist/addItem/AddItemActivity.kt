package com.lftf.simplelist.addItem

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.lftf.simplelist.R
import com.lftf.simplelist.data.DataManager
import com.lftf.simplelist.databinding.ActivityAddItemBinding
import com.lftf.simplelist.insertToolbar
import com.lftf.simplelist.models.ItemModel


class AddItemActivity : AppCompatActivity(), View.OnClickListener {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }

    lateinit var binding: ActivityAddItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertToolbar(binding.toolbar.root, "Criar novo item", true)

        with(binding){
            buttonSave.setOnClickListener(this@AddItemActivity)
            buttonCancel.setOnClickListener(this@AddItemActivity)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_save -> {
                val stringTitle = binding.fieldTitle.text.toString()
                val floatValue = binding.fieldValue.text.toString().toFloat()
                val intQuantity: Int = when (binding.fieldQuantity.text.toString()) {
                    "" -> 1
                    else -> binding.fieldQuantity.text.toString().toInt()
                }
                ItemModel(stringTitle, quantity = intQuantity, value = floatValue).apply {
                    DataManager.addItem(this)
                    finish()
                    Toast.makeText(this@AddItemActivity, "Item salvo", Toast.LENGTH_LONG).show()
                }
            }
            R.id.button_cancel -> alertCancel()
        }
    }

    private fun alertCancel() {
        AlertDialog.Builder(this)
            .setTitle("Cancelar")
            .setMessage("Tem certeza que quer cancelar o item? ")
            .setPositiveButton("Sim") { _, _ -> onBackPressed() }
            .setNegativeButton("Não") { _, _ -> binding.fieldTitle.requestFocus() }
            .show()
    }
}