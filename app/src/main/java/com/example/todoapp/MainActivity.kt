package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.ui.theme.TodoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoScreen()
        }
    }
}

@Composable
fun TodoScreen() {
    val items = remember { mutableStateListOf<String>() }
    val (text, setText) = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                ,horizontalArrangement = Arrangement.End
        ) {
            TodoList(
                items = items,
                modifier = Modifier.weight(1f)
            )

            TextField(
                value = text,
                onValueChange = setText,
                label = { Text("Enter a new task") },
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Button(

                onClick = {
                    items.add(text)
                    setText("")
                },
                modifier = Modifier.size(15.dp)
            ) {
                Text("Add")
            }
        }

    }

}

@Composable
fun TodoList(items: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items) { item ->
            Text(
                text = item,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}