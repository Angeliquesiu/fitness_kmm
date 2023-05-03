package com.example.fitnesswraps.data.local

import com.example.fitnesswraps.database.FitnessWrapsDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(FitnessWrapsDatabase.Schema, "fitnessWraps.db")
    }
}