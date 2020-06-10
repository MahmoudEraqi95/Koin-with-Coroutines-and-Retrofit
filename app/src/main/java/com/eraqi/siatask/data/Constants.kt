package com.eraqi.siatask.data

/**
* this class contains most of the constants that we might use in different classes in our app
 */
class Constants {
   companion object {
       val BASE_URL = "https://api.stackexchange.com/2.2/"
       val questions = listOf<String>("What is the topic you're searching for?",
           "What is the order you'd like to view the question in?",
           "How do you like the result to be sorted?")
       val answers = listOf<List<String>>(listOf("Android", "Kotlin", "Machine-Learning", "Go", "Spring-boot"),
           listOf("desc", "asc"),
           listOf("activity", "votes", "creation", "relevance"))

   }
}