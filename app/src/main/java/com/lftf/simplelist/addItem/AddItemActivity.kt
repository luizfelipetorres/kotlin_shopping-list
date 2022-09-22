package com.lftf.simplelist.addItem

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.lftf.simplelist.R
import com.lftf.simplelist.data.DataManager
import com.lftf.simplelist.databinding.ActivityAddItemBinding
import com.lftf.simplelist.insertToolbar
import com.lftf.simplelist.models.ItemModel
import com.lftf.simplelist.repository.ItemRepository

const val TAG = "AddItemActivity"

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

        with(binding) {
            buttonSave.setOnClickListener(this@AddItemActivity)
            buttonCancel.setOnClickListener(this@AddItemActivity)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_save -> {
                if (!validateInput())
                    return

                val stringQuantity = binding.fieldQuantity.text.toString()
                val item = ItemModel(
                    title = binding.fieldTitle.text.toString(),
                    quantity = if (stringQuantity == "") 1 else stringQuantity.toInt(),
                    value = binding.fieldValue.text.toString().toFloat()
                )
                DataManager.addItem(item)
                val repo = ItemRepository(this)
                Log.d(TAG, repo.save(item = item).toString())
                finish()
                Toast.makeText(this, "Item salvo", Toast.LENGTH_LONG).show()

            }
            R.id.button_cancel -> alertCancel()
        }
    }

    private fun validateInput(): Boolean {
        binding.fieldTitleLayout.let {
            if (binding.fieldTitle.text.isNullOrBlank()) {
                it.isErrorEnabled = true
                it.error = "Você precisa nomear o item!"
                return false
            }

            it.isErrorEnabled = false
            return true
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