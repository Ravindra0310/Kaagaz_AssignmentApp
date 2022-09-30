package com.ravi.myapplication.ui.Database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat
import java.util.*

@RunWith(AndroidJUnit4::class)

// Annotate with @RunWith
internal class ImageDatabaseTest : TestCase() {

    private lateinit var db: ImageDatabase
    private lateinit var dao: ImageDao


    // Override function setUp() and annotate it with @Before
    // this function will be called at first when this test class is called
    @Before
    override fun setUp() {
        // get context -- since this is an instrumental test it requires
        // context from the running application
        val context = ApplicationProvider.getApplicationContext<Context>()
        // initialize the db and dao variable
        db = Room.inMemoryDatabaseBuilder(context, ImageDatabase::class.java).build()
        dao = db.getImageDao()

    }

    // Override function closeDb() and annotate it with @After
    // this function will be called at last when this test class is called
    @After
    fun closeDb() {
        db.close()
    }

    // create a test function and annotate it with @Test
    // here we are first adding an item to the db and then checking if that item
    // is present in the db -- if the item is present then our test cases pass
    @Test
    @Throws(Exception::class)
    fun InsertAndGetImages() = runBlocking {
        var time = SimpleDateFormat("test", Locale.US)
            .format(System.currentTimeMillis())
        var image=ImageEntity("test",time,"testAlbums")
        dao.getTask()
        val images = dao.getTask().value
        images?.contains(image)?.let { assertThat("test", it) }
    }
}