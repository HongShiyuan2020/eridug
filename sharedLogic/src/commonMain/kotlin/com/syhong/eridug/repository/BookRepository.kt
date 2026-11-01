package com.syhong.eridug.repository

import com.syhong.eridug.core.database.AppDatabase
import com.syhong.eridug.core.database.createRoomDatabase
import com.syhong.eridug.core.database.getDatabaseBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class BookRepository (
    private val database: AppDatabase
) {
    private val bookDao = database.bookDao()
}