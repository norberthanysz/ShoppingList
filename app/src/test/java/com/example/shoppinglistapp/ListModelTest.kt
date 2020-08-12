package com.example.shoppinglistapp

import com.example.shoppinglistapp.models.ShoppingListModel
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ListModelTest {
    lateinit var list: ShoppingListModel

    @Before
    fun setup() {
        list = ShoppingListModel()
    }

    @Test
    fun addItem() {
        val beforeAdd = list.items.size
        val testItem = "test"
        list.items.add(testItem)
        val afterAdd = list.items.size
        assertEquals(afterAdd, beforeAdd + 1)
    }

    @Test
    fun deleteItem() {
        val index = 0
        val testItem = "test"
        list.items.add(testItem)
        val beforeDelete = list.items.size
        list.items.removeAt(index)
        val afterDelete = list.items.size
        assertEquals(afterDelete, beforeDelete - 1)
    }

}