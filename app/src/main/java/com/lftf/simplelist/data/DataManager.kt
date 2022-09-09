package com.lftf.simplelist.data

import com.lftf.simplelist.models.itemModel

/**
 * Singleton com dados
 */
object DataManager {
    fun getExamples(): List<itemModel> {
        val list = mutableListOf<itemModel>()
        list.add(itemModel("Sabão", value = 1f))
        list.add(itemModel("Batatas", value = 1f))
        list.add(itemModel("Cenoura", value = 1f))
        list.add(itemModel("Abacate", value = 1f))
        list.add(itemModel("Shampoo", value = 1f))
        list.add(itemModel("Sabonete", value = 1f))
        list.add(itemModel("Farinha", value = 1f))
        list.add(itemModel("Sucrilhos", value = 1f))
        list.add(itemModel("Aipim", value = 1f))
        list.add(itemModel("Soja", value = 1f))
        list.add(itemModel("Ervilha", value = 1f))
        list.add(itemModel("Milho", value = 1f))
        return list
    }
}