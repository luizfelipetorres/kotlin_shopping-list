package com.lftf.simplelist.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.lftf.simplelist.R
import com.lftf.simplelist.models.ItemModel
import com.lftf.simplelist.repository.ItemRepository

/**
 * Cria um adaptador. O adaptador cria objetos ViewHolder conforme necessário
 */
class RVAdapter(val context: Context, private val itemList: List<ItemModel>) :
    RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    /**
     * inner class (classe interna) viewHolder, manipulada por RVAdapter
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.text_view)
        private val buttonDelete: ImageButton = view.findViewById(R.id.button_delete)

        fun bind(item: ItemModel, position: Int) {
            val repo = ItemRepository(context)
            textView.text = """
            Titulo:     ${item.title} (${position})
            Quantidade: ${item.quantity}
            Valor:      R$ ${"%.2f".format(item.value)}
            Total:      R$ ${"%.2f".format(item.getTotalValue())}
            """.trimIndent()

            buttonDelete.setOnClickListener {
                if (repo.delete(item.id) > 0){
                    itemList as ArrayList<ItemModel>
                    itemList.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, itemCount)
                } else {
                    Toast.makeText(context, "não encontrado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    /**
     * Criar ViewHouders de R.layout.item
     */
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val item = LayoutInflater.from(p0.context).inflate(R.layout.item, p0, false)
        return ViewHolder(item)
    }

    /**
     * Acopla os dados de cada item do itemList no viewHolder
     */
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = itemList[position]
        viewHolder.bind(item, position)
    }

    /**
     * Tamanho da lista
     */
    override fun getItemCount(): Int = itemList.size
}
