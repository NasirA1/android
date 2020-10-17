package com.example.mvvmtut2

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mvvmtut2.data.ContactDatabase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.mvvmtut2", appContext.packageName)

        runBlocking {
//            ContactDatabase.getInstance(appContext).contactDao()
//                .insert(Contact(0, "Amber", "07921442312", "amber12@asia.com"))
            val contacts = ContactDatabase.getInstance(appContext).contactDao().getAll()
            println(contacts)
        }
    }
}