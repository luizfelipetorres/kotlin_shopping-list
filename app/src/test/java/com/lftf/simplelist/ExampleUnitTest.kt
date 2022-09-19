package com.lftf.simplelist

import com.lftf.simplelist.models.ItemModel
import org.junit.Assert
import org.junit.Test


class ExampleUnitTest {

    @Test
    fun testTotalValue() {
        Assert.assertEquals(8f, ItemModel("", 2, 4f).getTotalValue())
        Assert.assertEquals(3f, ItemModel("", 1, 3f).getTotalValue())
        Assert.assertEquals(10f, ItemModel("", 5, 2f).getTotalValue())
        Assert.assertEquals(20f, ItemModel("", value = 20f).getTotalValue())
    }
}