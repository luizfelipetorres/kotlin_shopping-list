package com.lftf.simplelist.addItem

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.lftf.simplelist.R
import com.lftf.simplelist.databinding.ActivityAddItemBinding
import com.lftf.simplelist.insertToolbar
import com.lftf.simplelist.models.ItemModel
import com.lftf.simplelist.repository.ItemRepository

const val TAG = "AddItemActivity"

/**
 * Estamos extendendo OnClickListener para colocar todas as ações de click em uma única fun
 */
class AddItemActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityAddItemBinding

    /**
     * Controla o que acontece ao clicar em um item de menu, que está na toolbar
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }

    /**
     * Início do ciclo de vida
     */
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

    /**
     * Ações de click, a depender do id da view clicada
     */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_save -> {
                if (!validateInput())
                    return

                val stringQuantity = binding.fieldQuantity.text.toString()
                val stringValue = binding.fieldValue.text.toString()
                val item = ItemModel(
                    title = binding.fieldTitle.text.toString(),
                    quantity = if (stringQuantity.isNullOrBlank()) 1 else stringQuantity.toInt(),
                    value = if (stringValue.isNullOrBlank()) 0f else stringValue.toFloat()
                )
                val repo = ItemRepository(this)
                val id = repo.save(item = item)
                Log.d(TAG, "Registro criado: ${id}")
                if (id > 0) {
                    AlertDialog.Builder(this)
                        .setTitle("Sucesso")
                        .setMessage("Registro criado com sucesso!\n quer cadastrar mais um?")
                        .setIcon(R.drawable.ic_baseline_done_green_24)
                        .setPositiveButton("Sim") { _, _ -> clearFields() }
                        .setNegativeButton("Não") { _, _ -> finish() }
                        .show()
                }
            }
            R.id.button_cancel -> alertCancel()
        }
    }

    /**
     * Limpar campos e focar em fieldTitle
     */
    private fun clearFields() {
        with(binding) {
            fieldTitle.setText("")
            fieldQuantity.setText("")
            fieldValue.setText("")
            fieldTitle.requestFocus()
        }
    }

    /**
     * Validar se os campos estão corretos e inserir erro se não estiverem
     */
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

    /**
     * Criar alerta de cancelamento
     */
    private fun alertCancel() {
        AlertDialog.Builder(this)
            .setTitle("Cancelar")
            .setMessage("Tem certeza que quer cancelar o item? ")
            .setPositiveButton("Sim") { _, _ -> onBackPressed() }
            .setNegativeButton("Não") { _, _ -> binding.fieldTitle.requestFocus() }
            .show()
    }
}