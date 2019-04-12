package be.maximedupierreux.todoapplication.android.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class Todo(@PrimaryKey(autoGenerate = true) val id : Int = 0,
                @ColumnInfo(name = "title") var title: String,
                @ColumnInfo(name = "details") var details : String,
                @ColumnInfo(name = "done") var done : Boolean)