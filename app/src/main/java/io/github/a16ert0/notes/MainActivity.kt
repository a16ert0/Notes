package io.github.a16ert0.notes

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.a16ert0.notes.ui.theme.NotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val notes = mutableListOf<String>()
        setContent {
            NotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(notes) {
                        notes.add(it)
                    }
                }
            }
        }
    }
}


@Composable
fun MainScreen(notes: List<String>, onClick: (String) -> Unit) {
    var input by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
//        Column(
//            modifier = Modifier.weight(1f)
//        ) {
//            notes.forEach {
//                Text(text = it)
//            }
//        }
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(notes) {
                Text(text = it)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(modifier = Modifier.weight(1f), value = input, onValueChange = {
                input = it
            })
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                Log.e("MainActivity", "onClick: $input")
                onClick(input)
            }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_send_24),
                    contentDescription = "Send Button"
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotesTheme {
        MainScreen(
            listOf(
                " 244",
                "4344"
            )
        ) {}
    }
}