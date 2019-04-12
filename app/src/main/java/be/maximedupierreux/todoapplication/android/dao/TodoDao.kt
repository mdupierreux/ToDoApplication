package be.maximedupierreux.todoapplication.android.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import be.maximedupierreux.todoapplication.android.entities.Todo

@Dao
interface TodoDao {

    @Query("SELECT * from todo_table order by done desc")
    fun getAllTodos() : LiveData<List<Todo>>

    @Insert
    suspend fun insert(todo : Todo)

    @Update
    suspend  fun update(todo: Todo)

    @Query("DELETE FROM todo_table")
    fun deleteAll()
}