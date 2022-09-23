package com.lftf.simplelist.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import com.lftf.simplelist.R
import com.lftf.simplelist.addItem.AddItemActivity
import com.lftf.simplelist.databinding.ItemBinding
import com.lftf.simplelist.models.ItemModel
import com.lftf.simplelist.repository.ItemRepository

/**
 * Cria um adaptador. O adaptador cria objetos ViewHolder conforme necessário
 */

const val TAG = "RVAdapter"

class RVAdapter(val context: Context, private val itemList: List<ItemModel>) :
    RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    private val repo = ItemRepository(context)
    private val ID = ItemModel.DatabaseDefinition.Columns.ID
    lateinit var bindingItem: ItemBinding

    /**
     * inner class (classe interna) viewHolder, manipulada por RVAdapter
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: ItemModel) {
            with(bindingItem) {
                val strValue: String

                textViewTitle.text = "${item.title}"
                textViewValue.text = context.getString(
                    if (item.quantity > 1)
                        R.string.value_value_total
                    else
                        R.string.value
                ).format(item.value, item.getTotalValue())
                textViewQuantity.text = "${item.quantity}"
                buttonDelete.setOnClickListener { onDelete(item) }

                arrayOf(
                    textViewQuantity,
                    textViewTitle,
                    textViewValue,
                    icQuantity,
                    icMoney,
                    icShoppintBag
                ).forEach { view: View -> view.setOnClickListener { onEdit(item.id) } }

                if (item.value == 0f) {
                    root.setCardBackgroundColor(Color.argb(50, 255, 0, 0))
                }
            }
        }

        /**
         * Chama o AddItemActivity no modo edição
         */
        private fun onEdit(id: Int) {
            val intent = Intent(context, AddItemActivity::class.java).apply {
                AddItemActivity.Constants.let {
                    this.putExtra(ID, id)
                    this.putExtra(it.TOOLBAR_TITLE, it.ATUALIZAR)
                }
            }
            ContextCompat.startActivity(context, intent, null)
        }


        private fun onDelete(item: ItemModel) {
            val count = repo.delete(item.id)
            Log.d(
                TAG, """
                |
                |item.id: ${item.id}
                |position: $adapterPosition
                |count: $count
                |""".trimMargin()
            )
            if (count > 0) {
                itemList as ArrayList<ItemModel>
                itemList.removeAt(adapterPosition)

                notifyItemRemoved(adapterPosition)
            } else {
                Toast.makeText(context, "não encontrado", Toast.LENGTH_SHORT).show()
            }
        }
    }


    /**
     * Criar ViewHouders de R.layout.item
     */
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        //val item = LayoutInflater.from(p0.context).inflate(R.layout.item, p0, false)
        bindingItem = ItemBinding.inflate(LayoutInflater.from(context), p0, false)
        return ViewHolder(bindingItem.root)
    }

    /**
     * Acopla os dados de cada item do itemList no viewHolder
     */
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = itemList[position]
        viewHolder.bind(item)
    }

    /**
     * Tamanho da lista
     */
    override fun getItemCount(): Int = itemList.size

}
