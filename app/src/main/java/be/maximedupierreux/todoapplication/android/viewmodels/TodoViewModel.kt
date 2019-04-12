package be.maximedupierreux.todoapplication.android.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import be.maximedupierreux.todoapplication.android.NotificationHelper
import be.maximedupierreux.todoapplication.android.TodoRoomDatabase
import be.maximedupierreux.todoapplication.android.entities.Todo
import be.maximedupierreux.todoapplication.android.repositories.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class TodoViewModel(application: Application) : AndroidViewModel(application){
    private val todoRepository : TodoRepository
    val todos : LiveData<List<Todo>>

    private var job = Job()
    private val coroutineContext: CoroutineContext get() = job + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)
    val notificationHelper = NotificationHelper(getApplication())

    init {
        val todoDao = TodoRoomDatabase.getDatabase(application).todoDao()
        todoRepository = TodoRepository(todoDao)
        todos = todoRepository.todos
        notificationHelper.setUpNotificationChannels()
    }

    fun insert(todo: Todo) = scope.launch(Dispatchers.IO) {
        todoRepository.insert(todo)
    }

    fun showBubble() = scope.launch (Dispatchers.Default){

        if(notificationHelper.canBubble()){
            notificationHelper.showNotification(false)
        }
    }

    fun toggleDone(todo : Todo, checked : Boolean) = scope.launch(Dispatchers.IO){
        todo.done = checked
        todoRepository.update(todo)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }


}