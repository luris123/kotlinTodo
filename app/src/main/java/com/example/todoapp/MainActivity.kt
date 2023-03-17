package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoApp(viewModel)
        }
    }

    @Composable
    fun TodoApp(viewModel: TodoViewModel) {
        Column(modifier = Modifier.fillMaxSize()) {
            TodoInput(onAddItem = { title ->
                viewModel.addItem(title)
            })
            TodoList(items = viewModel.items, onItemRemove = { item ->
                viewModel.removeItem(item)
            })
        }
    }

    @Composable
    fun TodoList(items: List<TodoItem>, onItemRemove: (TodoItem) -> Unit) {
        LazyColumn {
            items(items = items) { item ->
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = item.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth(0.7f)
                            .weight(1f),
                        style = MaterialTheme.typography.body1
                    )
                    IconButton(onClick = { onItemRemove(item) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Remove")
                    }
                }
            }
        }
    }

    @Composable
    fun TodoInput(onAddItem: (String) -> Unit) {
        val textState = remember { mutableStateOf("") }
        Row {
            OutlinedTextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                label = { Text("Enter a new Todo item") },
                modifier = Modifier.weight(0.8f)
            )
            Button(onClick = {
                onAddItem(textState.value)
                textState.value = ""
            },
            modifier = Modifier.height(56.dp)
            ) {
                Text("Add")
            }
        }
    }
}


