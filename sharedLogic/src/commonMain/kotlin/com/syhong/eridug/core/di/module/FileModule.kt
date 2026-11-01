package com.syhong.eridug.core.di.module

import com.syhong.eridug.core.file.FileManager
import org.koin.dsl.module

val fileModule = module {
    single<FileManager> {
        FileManager()
    }
}