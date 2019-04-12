package be.maximedupierreux.todoapplication.android

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import be.maximedupierreux.todoapplication.android.entities.Todo
import kotlinx.android.synthetic.main.todo_item.view.*

class TodoRecyclerAdapter(context : Context, val onClick: (Todo, Boolean) -> Unit) : RecyclerView.Adapter<TodoRecyclerAdapter.TodoViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private var todos = emptyList<Todo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = inflater.inflate(R.layout.todo_item,parent,false)
        return TodoViewHolder(itemView)
    }

    override fun getItemCount() = todos.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bindTodo(todos[position])
        holder.itemView.cbDone.setOnClickListener {
            onClick(todos[position],(it as CompoundButton).isChecked)
        }
    }

    fun setTodos(todos : List<Todo>){
        this.todos = todos
        notifyDataSetChanged()
    }


    inner class TodoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindTodo(todo: Todo) {
            with(todo){
                itemView.tvTodo.text = title
                itemView.cbDone.isChecked = done
            }
        }

    }
}