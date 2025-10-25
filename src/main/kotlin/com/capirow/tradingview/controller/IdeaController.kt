package com.capirow.tradingview.controller

import com.capirow.tradingview.model.IdeaEntity
import com.capirow.tradingview.repository.IdeaRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/ideas")
class IdeaController(private val repository: IdeaRepository) {

    @GetMapping
    fun getAll(): List<IdeaEntity> = repository.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): IdeaEntity =
        repository.findById(id).orElseThrow { RuntimeException("Idea not found") }
}
