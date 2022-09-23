package com.lftf.simplelist.addItem

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
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
    lateinit var toolbarTitle: String

    companion object {
        val TOOLBAR_TITLE = "TOOLBAR_TITLE"
        val ATUALIZAR = "Atualizar"
        val CRIAR_ITEM = "Criar item"
    }


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

        val id = intent.getIntExtra(ItemModel.DatabaseDefinition.Columns.ID, 0)
        toolbarTitle = intent.getStringExtra(TOOLBAR_TITLE).toString()
        Log.d(TAG, "id: $id")

        if (toolbarTitle == ATUALIZAR) {
            val repo = ItemRepository(this)
            val item = repo.getItem(id)
            changeFields(item)
            binding.fieldValue.requestFocus()
        }

        insertToolbar(binding.toolbar.root as Toolbar, toolbarTitle, true)


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

                val item = ItemModel(
                    id = intent.getIntExtra(ItemModel.DatabaseDefinition.Columns.ID, 0),
                    title = binding.fieldTitle.text.toString(),
                    quantity = binding.fieldQuantity.text.toString().let {
                        if (it.isBlank()) 1 else it.toInt()
                    },
                    value = binding.fieldValue.text.toString().let {
                        if (it.isBlank()) 0f else it.toFloat()
                    }
                )
                val repo = ItemRepository(this)
                val id: Int
                if (toolbarTitle == ATUALIZAR) {
                    id = repo.update(item)
                    Toast.makeText(
                        this,
                        """Item ${item.title} atualizado!
                        |Quantidade: ${item.quantity}
                        |valor: R$ ${"%.2f".format(item.value)}
                        |Total: R$ ${"%.2f".format(item.getTotalValue())}
                        """.trimMargin(),
                        Toast.LENGTH_LONG
                    ).show()
                    onBackPressed()
                } else {
                    id = repo.save(item = item)
                    if (id > 0) {
                        AlertDialog.Builder(this)
                            .setTitle("Sucesso")
                            .setMessage("Registro criado com sucesso!\n quer cadastrar mais um?")
                            .setIcon(R.drawable.ic_baseline_done_green_24)
                            .setPositiveButton("Sim") { _, _ -> changeFields(null) }
                            .setNegativeButton("Não") { _, _ -> finish() }
                            .show()
                    }
                }
            }
            R.id.button_cancel -> alertCancel()
        }
    }

    /**
     * Limpar campos e focar em fieldTitle
     */
    private fun changeFields(item: ItemModel?) {
        if (item == null) {
            with(binding) {
                fieldTitle.setText("")
                fieldQuantity.setText("")
                fieldValue.setText("")
                fieldTitle.requestFocus()
            }
        } else {
            with(binding) {
                fieldTitle.setText(item.title)
                fieldQuantity.setText(item.quantity.toString().let { if (it == "1") "" else it })
                fieldValue.setText(item.value.toString().let { if (it == "0.0") "" else it })
            }
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