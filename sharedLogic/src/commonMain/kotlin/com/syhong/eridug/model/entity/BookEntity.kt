package com.syhong.eridug.model.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class BookEntity(
    @PrimaryKey
    val id: Long = 0,
    val title: String,
    val author: String,
    val coverUrl: String,
    val description: String,
    val rating: Double,
) {
}