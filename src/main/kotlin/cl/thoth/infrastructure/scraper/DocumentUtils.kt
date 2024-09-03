package cl.thoth.infrastructure.scraper

import org.jsoup.nodes.Document

object DocumentUtils {
    fun extractText(doc: Document, selector: String): String {
        return doc.selectFirst(selector)?.text().orEmpty()
    }

    fun extractImageUrl(doc: Document, selector: String): String {
        return doc.selectFirst(selector)?.attr("src").orEmpty()
    }

    fun extractData(doc: Document, searchTerm: String, attribute: String = "text"): String? {
        val selector = if (attribute == "href") {
            "dt:contains($searchTerm) + dd a"
        } else {
            "dt:contains($searchTerm) + dd"
        }
        return doc.selectFirst(selector)?.let {
            if (attribute == "href") it.attr("href") else it.text()
        }
    }
}
