package com.lftf.simplelist.data

import android.content.Context
import com.lftf.simplelist.models.ItemModel
import com.lftf.simplelist.repository.ItemRepository

/**
 * Singleton com dados
 */
class DataManager(val context: Context) {
    val list = mutableListOf<ItemModel>()
    init {
        getExamples()
    }

    fun addItem(itemModel: ItemModel) {
        list.add(itemModel)
    }

    fun addItens() {
        val repo = ItemRepository(context)
        list.forEach { i -> repo.save(i) }
    }

    fun updateItem(position: Int, item: ItemModel) {
        list[position] = item
    }

    fun deleteItem(position: Int) {
        list.removeAt(position)
    }

    private fun getExamples(): List<ItemModel> {
        with(list) {
            add(ItemModel(title = "item 1", value = 0f))
            add(ItemModel(title = "item 2", value = 0f))
            add(ItemModel(title = "item 3", value = 0f))
            add(ItemModel(title = "item 4", value = 0f))
            add(ItemModel(title = "item 5", value = 0f))
            add(ItemModel(title = "item 6", value = 0f))
            add(ItemModel(title = "item 7", value = 0f))
            add(ItemModel(title = "item 8", value = 0f))
        }
        return list
    }
}