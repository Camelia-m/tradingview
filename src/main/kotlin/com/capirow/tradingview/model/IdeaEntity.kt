package com.capirow.tradingview.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class IdeaEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val title: String,
    val author: String?,
    val publicationDate: String?,
    val boostsCount: Int?,
    val ideaStrategy: String?,
    val symbol: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Lob
    val jsonData: String // store full JSON of the idea
)
