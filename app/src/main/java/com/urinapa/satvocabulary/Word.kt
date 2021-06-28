package com.urinapa.satvocabulary

data class Word(
    val word: String,
    val definition: List<String>,
    val example: List<String>,
    val successCount: Int,
    val failureCount: Int
    )
