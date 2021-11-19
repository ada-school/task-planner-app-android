package org.adaschool.taskplanner

import android.content.SharedPreferences
import org.adaschool.taskplanner.storage.SharedPreferencesStorage
import org.adaschool.taskplanner.utils.TOKEN_KEY
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class SharedPreferencesStorageTest {

    @Mock
    lateinit var sharedPreferences: SharedPreferences

    @Mock
    lateinit var editor: SharedPreferences.Editor

    lateinit var sharedPreferencesStorage: SharedPreferencesStorage

    @Before
    fun setup() {
        sharedPreferencesStorage = SharedPreferencesStorage(sharedPreferences)
        `when`(sharedPreferences.edit()).thenReturn(editor)
    }

    @Test
    fun `save token edits preferences and calls apply function`() {
        val token = "test_token"
        `when`(editor.putString(TOKEN_KEY, token)).thenReturn(editor)
        sharedPreferencesStorage.saveToken(token)
        verify(sharedPreferences).edit()
        verify(editor).putString(TOKEN_KEY, token)
        verify(editor).apply()
    }

    @Test
    fun `get token returns token from TOKEN_KEY`() {
        val token = "token"
        `when`(sharedPreferences.getString(TOKEN_KEY, "")).thenReturn(token)
        sharedPreferencesStorage.getToken()
        verify(sharedPreferences).getString(TOKEN_KEY, "")
    }

    @Test
    fun `clear removes TOKEN_KEY from shared preferences`() {
        `when`(editor.remove(TOKEN_KEY)).thenReturn(editor)
        sharedPreferencesStorage.clear()
        verify(sharedPreferences).edit()
        verify(editor).remove(TOKEN_KEY)
        verify(editor).apply()
    }

    @Test
    fun `isUserAuth returns true when TOKEN_KEY is not empty`() {
        `when`(sharedPreferences.getString(TOKEN_KEY, "")).thenReturn("token")
        val userAuth = sharedPreferencesStorage.isUserAuth()
        assertTrue(userAuth)
    }

    @Test
    fun `isUserAuth returns false when TOKEN_KEY is empty`() {
        `when`(sharedPreferences.getString(TOKEN_KEY, "")).thenReturn("")
        val userAuth = sharedPreferencesStorage.isUserAuth()
        assertFalse(userAuth)
    }

    @Test
    fun `when token is exception then runtime exception thrown`() {
        assertThrows(RuntimeException::class.java) {
            sharedPreferencesStorage.saveToken("exception")
        }

    }
}