package com.capirow.tradingview.repository

import com.capirow.tradingview.model.IdeaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IdeaRepository : JpaRepository<IdeaEntity, Long> {
    fun findByTitle(title: String): IdeaEntity?
}
