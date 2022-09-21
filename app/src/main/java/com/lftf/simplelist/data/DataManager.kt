package com.lftf.simplelist.data

import com.lftf.simplelist.models.ItemModel

/**
 * Singleton com dados
 */
object DataManager {
    private val list = mutableListOf<ItemModel>()
    fun getList() = list

    init {
        getExamples()
    }

    fun addItem(itemModel: ItemModel) {
        list.add(itemModel)
    }

    fun updateItem(position: Int, item: ItemModel){
        list[position] = item
    }

    fun deleteItem(position: Int) {
        list.removeAt(position)
    }

    private fun getExamples(): List<ItemModel> {
        with(list) {
            add(ItemModel("Sabão", value = 1f))
            add(ItemModel("Batatas", value = 1f))
            add(ItemModel("Cenoura", value = 1f))
            add(ItemModel("Abacate", value = 1f))
            add(ItemModel("Shampoo", value = 1f))
            add(ItemModel("Sabonete", value = 1f))
            add(ItemModel("Farinha", value = 1f))
            add(ItemModel("Sucrilhos", value = 1f))
            add(ItemModel("Aipim", value = 1f))
            add(ItemModel("Soja", value = 1f))
            add(ItemModel("Ervilha", value = 1f))
            add(ItemModel("Milho", value = 1f))
        }
        return list
    }
}