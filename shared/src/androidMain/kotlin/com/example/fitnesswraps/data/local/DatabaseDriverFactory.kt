package com.example.fitnesswraps.data.local

import android.content.Context
import com.example.fitnesswraps.database.FitnessWrapsDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(FitnessWrapsDatabase.Schema, context, "fitnessWraps.db")
    }
}