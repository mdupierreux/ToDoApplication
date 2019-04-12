package be.maximedupierreux.todoapplication.android.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import be.maximedupierreux.todoapplication.android.dao.TodoDao
import be.maximedupierreux.todoapplication.android.entities.Todo

class TodoRepository(private val todoDao: TodoDao) {

    val todos: LiveData<List<Todo>> = todoDao.getAllTodos()

    @WorkerThread
    suspend fun insert(todo : Todo){
        todoDao.insert(todo)
    }

    @WorkerThread
    suspend fun update(todo: Todo){
        todoDao.update(todo)
    }
}