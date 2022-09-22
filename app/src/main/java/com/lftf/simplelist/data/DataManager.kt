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
            add(ItemModel(title = "Sabão", value = 1f))
            add(ItemModel(title ="Batatas", value = 1f))
            add(ItemModel(title ="Cenoura", value = 1f))
            add(ItemModel(title ="Abacate", value = 1f))
            add(ItemModel(title ="Shampoo", value = 1f))
            add(ItemModel(title ="Sabonete", value = 1f))
            add(ItemModel(title ="Farinha", value = 1f))
            add(ItemModel(title ="Sucrilhos", value = 1f))
            add(ItemModel(title ="Aipim", value = 1f))
            add(ItemModel(title ="Soja", value = 1f))
            add(ItemModel(title ="Ervilha", value = 1f))
            add(ItemModel(title ="Milho", value = 1f))
        }
        return list
    }
}