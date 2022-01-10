package kg.rickandmorty.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Exception

@ExperimentalCoroutinesApi
@SmallTest
@RunWith(AndroidJUnit4::class)
class CharacterDaoTest: LocalDatabase() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var characterDAO: CharacterDAO

    @Before
    fun setup(){
        characterDAO = database.characterDao()
    }

    @Test
    @Throws(Exception::class)
    fun insert() = runBlockingTest{
        val character = CharacterTest.createCharacter()
        characterDAO.insertCharacter(character)

        val result = characterDAO.getAll()
        assertThat(result, notNullValue())
    }

    @Test
    @Throws(Exception::class)
    fun delete() = runBlockingTest {
        val character = CharacterTest.createCharacter()
        characterDAO.insertCharacter(character)

        var result = characterDAO.getAll()
        assertThat(result, notNullValue())

        characterDAO.deleteCharacter(character)
        result = characterDAO.getAll()

        assertThat(result.first().isEmpty(), `is`(true))
    }
}