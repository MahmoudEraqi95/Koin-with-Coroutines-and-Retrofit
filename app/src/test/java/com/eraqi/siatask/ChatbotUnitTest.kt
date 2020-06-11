package com.eraqi.siatask

import android.content.Context
import com.eraqi.siatask.data.Constants
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock


@RunWith(JUnit4::class)
class ChatbotUnitTest {

    @Test
    fun testQuestionAndAnswersRetrieved(){
        assertEquals(Constants.questions.size, Constants.answers.size)
    }






}