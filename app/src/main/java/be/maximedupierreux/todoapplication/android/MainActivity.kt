package be.maximedupierreux.todoapplication.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import be.maximedupierreux.todoapplication.android.entities.Todo
import be.maximedupierreux.todoapplication.android.viewmodels.TodoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.todo_item.*

class MainActivity : AppCompatActivity() {
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = rvTodos
        val adapter = TodoRecyclerAdapter(this) {todo, done ->
            todoViewModel.toggleDone(todo, done)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        todoViewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)

        todoViewModel.todos.observe(this, Observer { todos ->
            todos?.let{
                adapter.setTodos(todos)
            }
        })


        todoViewModel.showBubble()


        fabAdd.setOnClickListener {
            if(!TextUtils.isEmpty(editTodo.text)){
                val todo = Todo(0, editTodo.text.toString(),"",false)
                todoViewModel.insert(todo)
            }
        }
    }
}
