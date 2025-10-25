package com.capirow.tradingview.service


import com.capirow.tradingview.model.IdeaEntity
import com.capirow.tradingview.repository.IdeaRepository
import com.capirow.tradingview.scraper.TradingViewClient
import com.capirow.tradingview.scraper.TradingViewParser
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class ScraperService(
    private val client: TradingViewClient,
    private val parser: TradingViewParser,
    private val repo: IdeaRepository
) {

    private val mapper = jacksonObjectMapper()

    @Scheduled(fixedRate = 3_600_000) // every hour
    fun fetchIdeas() {
        val symbol = "BTCUSD"
        val response = client.api.getIdeas(symbol).execute()
        if (!response.isSuccessful) {
            println("❌ Failed to fetch ideas: ${response.code()}")
            return
        }

        val ideas = parser.parseIdeas(response.body() ?: emptyMap())

        ideas.forEach { idea ->
            val title = idea["name"]?.toString() ?: idea["title"]?.toString() ?: return@forEach
            if (repo.findByTitle(title) == null) {
                val jsonData = mapper.writeValueAsString(idea)
                val entity = IdeaEntity(
                    title = title,
                    author = idea["author"]?.toString(),
                    publicationDate = idea["published_at"]?.toString(),
                    boostsCount = (idea["boost_count"]?.toString()?.toIntOrNull()),
                    ideaStrategy = idea["strategy"]?.toString(),
                    symbol = symbol,
                    jsonData = jsonData
                )
                repo.save(entity)
                println("🆕 Saved new idea: $title")
            }
        }

        println("✅ Scraper completed for $symbol")
    }
}
