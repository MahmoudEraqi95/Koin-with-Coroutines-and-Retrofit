package com.eraqi.siatask

import android.content.Context
import android.provider.SyncStateContract
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.eraqi.siatask.data.Constants
import com.eraqi.siatask.ui.adapter.ChatbotAdapter
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 *this class is to perform an Instrumented test, please note that you need to have a running android device connected to you computer
 *
 */
@RunWith(AndroidJUnit4::class)
class ChatbotIntrumentedTest {
    lateinit var appContext: Context
    lateinit var chatbotAdapter:ChatbotAdapter
    @Before
    fun setUp(){
         appContext = InstrumentationRegistry.getInstrumentation().targetContext
         chatbotAdapter = ChatbotAdapter(appContext)
    }

    @Test
    fun testValidateAddingQuestion(){
        assertFalse(chatbotAdapter.validateAddingNewQuestion(Constants.questions.size+1))
        assertTrue(chatbotAdapter.validateAddingNewQuestion(Constants.questions.size))

    }
    @Test
    fun testAddingQestionToAdapter(){
        chatbotAdapter.addQuestion(0)
        assertEquals(1, chatbotAdapter.question.size)
    }

}