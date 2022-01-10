package kg.rickandmorty.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
open class LocalDatabase {

    lateinit var database: CharacterDatabase

    @Before
    fun createDB(){
        database = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            CharacterDatabase::class.java
        ).allowMainThreadQueries()
            .build()
    }

    @After
    @Throws(IOException::class)
    fun tearDown(){
        database.close()
    }
}