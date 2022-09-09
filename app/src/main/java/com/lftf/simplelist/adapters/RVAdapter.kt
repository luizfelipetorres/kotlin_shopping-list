package com.lftf.simplelist.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lftf.simplelist.R
import com.lftf.simplelist.models.itemModel

/**
 * Cria um adaptador. O adaptador cria objetos ViewHolder conforme necessário
 */
class RVAdapter(val itemList: List<itemModel>): RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    /**
     * nested class (classe interna) viewHolder, manipulada por RVAdapter
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.text_view)
    }

    /**
     * Criar ViewHouders de R.layout.item
     */
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val item = LayoutInflater.from(p0.context).inflate(R.layout.item,p0, false)
        return ViewHolder(item)
    }

    /**
     * Acopla os dados de itemList na viewHolder criada
     */
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = itemList[position]
        viewHolder.textView.text = """
            Titulo:     ${item.title} ${position}
            Quantidade: ${item.quantity}
            Valor:      R$ ${"%.2f".format(item.value)}
            Total:      R$ ${"%.2f".format(item.getTotalValue())}
        """.trimIndent()
    }

    /**
     * Tamanho da lista
     */
    override fun getItemCount(): Int {
        return itemList.size
    }
}
