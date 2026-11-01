package com.syhong.eridug.model.entity

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.Index
import androidx.room3.PrimaryKey
import kotlinx.serialization.Serializable


@Serializable
@Entity(
    tableName = "plugins",
    indices = [
        Index(value = ["name"], unique = true)
    ]
)
data class PluginEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    val version: String = "",
    val name: String = "",
    val type: String = "",
    val code: String = "",
) {
}