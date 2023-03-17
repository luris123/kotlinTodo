package com.example.todoapp

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.todoapp.TodoItem


class TodoViewModel : ViewModel() {
    private val _items = mutableStateListOf<TodoItem>()
    val items: List<TodoItem> get() = _items

    fun addItem(title: String) {
        _items.add(TodoItem(title = title, isCompleted = false))
    }

    fun removeItem(item: TodoItem) {
        _items.remove(item)
    }

    fun updateItemCompletion(item: TodoItem, isCompleted: Boolean) {
        val index = _items.indexOf(item)
        _items[index] = item.copy(isCompleted = isCompleted)
    }
}
