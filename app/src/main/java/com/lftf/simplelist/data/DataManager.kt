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
        return list
    }
}