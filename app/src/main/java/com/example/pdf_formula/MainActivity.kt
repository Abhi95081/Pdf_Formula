package com.example.pdf_formula


import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.pdf_formula.ui.theme.Pdf_FormulaTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Simulate reading tasks from a PDF (replace with actual PDF parsing logic)
        val tasksFromPdf = listOf(
            "Review the introduction of the document",
            "Extract key points from section 1",
            "Summarize the conclusions"
        )

        enableEdgeToEdge()
        setContent {
            Pdf_FormulaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ToDoListScreen(tasksFromPdf)
                }
            }
        }
    }
}

@Composable
fun ToDoListScreen(initialTasks: List<String>) {
    var tasks by remember { mutableStateOf(initialTasks.map { Task(it) }) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "To-Do List",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(tasks.size) { index ->
                TaskItem(
                    task = tasks[index],
                    onCheckedChange = { isChecked ->
                        tasks = tasks.mapIndexed { i, task ->
                            if (i == index) task.copy(isCompleted = isChecked) else task
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = onCheckedChange,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = task.description, fontSize = 18.sp)
        }
    }
}

data class Task(
    val description: String,
    val isCompleted: Boolean = false
)


suspend fun parsePdf(filePath: String): List<String> {
    return withContext(Dispatchers.IO) {
        // PDF parsing logic here
        listOf("Task 1 from PDF", "Task 2 from PDF")
    }
}