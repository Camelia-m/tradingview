package com.capirow.tradingview.scraper

import org.springframework.stereotype.Component

@Component
class TradingViewParser {
    fun parseIdeas(json: Map<String, Any>): List<Map<String, Any?>> {
        val data = json["data"] as? Map<*, *> ?: return emptyList()
        val ideas = ((data["ideas"] as? Map<*, *>)?.get("data") as? Map<*, *>)?.get("items") as? List<Map<String, Any?>>
        return ideas ?: emptyList()
    }
}