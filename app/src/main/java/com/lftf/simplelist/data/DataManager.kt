package com.lftf.simplelist.data

import com.lftf.simplelist.models.itemModel

/**
 * Singleton com dados
 */
object DataManager {
    private val list = mutableListOf<itemModel>()
    fun getList() = list

    init {
        getExamples()
    }

    fun addItem(itemModel: itemModel) {
        list.add(itemModel)
    }

    fun deleteItem(position: Int) {
        list.removeAt(position)
    }


    private fun getExamples(): List<itemModel> {
        with(list) {
            add(itemModel("Sabão", value = 1f))
            add(itemModel("Batatas", value = 1f))
            add(itemModel("Cenoura", value = 1f))
            add(itemModel("Abacate", value = 1f))
            add(itemModel("Shampoo", value = 1f))
            add(itemModel("Sabonete", value = 1f))
            add(itemModel("Farinha", value = 1f))
            add(itemModel("Sucrilhos", value = 1f))
            add(itemModel("Aipim", value = 1f))
            add(itemModel("Soja", value = 1f))
            add(itemModel("Ervilha", value = 1f))
            add(itemModel("Milho", value = 1f))
        }
        return list
    }
}