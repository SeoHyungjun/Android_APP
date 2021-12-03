package hyungjun.seo.app.calculator

import androidx.room.Database
import androidx.room.RoomDatabase
import hyungjun.seo.app.calculator.dao.HistoryDao
import hyungjun.seo.app.calculator.model.History

@Database(entities = [History::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}