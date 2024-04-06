package com.example.kotlin_todoapp
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TodoAdapter
    private val todoList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addButton: Button = findViewById(R.id.addButton)
        val removeAllButton: Button = findViewById(R.id.removeAllButton)
        val editText: EditText = findViewById(R.id.editText)

        recyclerView = findViewById(R.id.recyclerView)
        adapter = TodoAdapter(todoList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        addButton.setOnClickListener {
            val newTodo = editText.text.toString()
            if (newTodo.isNotBlank()) {
                todoList.add(newTodo)
                adapter.notifyDataSetChanged()
                editText.text.clear()
            }
        }

        removeAllButton.setOnClickListener {
            todoList.clear()
            adapter.notifyDataSetChanged()
        }
    }
}
