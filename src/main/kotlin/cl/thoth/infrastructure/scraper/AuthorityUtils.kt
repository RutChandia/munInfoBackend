package cl.thoth.infrastructure.scraper

import cl.thoth.domain.model.Authorities
import cl.thoth.domain.model.PoliticalAuthority
import org.jsoup.nodes.Document

object AuthorityUtils {
    fun extractAuthorities(doc: Document): Authorities {
        val mayor = getMayor(doc)
        return Authorities(
            mayor = mayor.first,
            mayorPhoto = mayor.second,
            councilors = extractAuthoritiesList(doc),
            senators = extractAuthorities(doc, "Senadores"),
            deputies = extractAuthorities(doc, "Diputados"),
            regionalGovernor = doc.selectFirst(".info_box:contains(Gobernador Regional) p")?.text().orEmpty(),
            cores = extractAuthorities(doc, "CORES")
        )
    }

    private fun getMayor(doc: Document): Pair<PoliticalAuthority, String> {
        val mayor = PoliticalAuthority(
            name = DocumentUtils.extractText(doc, ".nombre_alcalde h3"),
            politicalParty = DocumentUtils.extractText(doc, ".nombre_alcalde h4:nth-of-type(2)")
        )
        val mayorPhoto = DocumentUtils.extractImageUrl(doc, "div.img_alcalde img")
        return mayor to mayorPhoto
    }

    private fun extractAuthoritiesList(doc: Document, selector: String = ".despliegue_wrap .file"): List<PoliticalAuthority> {
        return doc.select(selector).mapNotNull { element ->
            val name = element.selectFirst(".col_nom")?.text().orEmpty()
            val politicalParty = element.selectFirst(".col_partido")?.text().orEmpty()
            if (name.isNotBlank() && politicalParty.isNotBlank()) {
                PoliticalAuthority(name, politicalParty)
            } else {
                null
            }
        }
    }

    private fun extractAuthorities(doc: Document, searchTerm: String): List<PoliticalAuthority> {
        return doc.select(".info_box:contains($searchTerm) p").map { element ->
            val info = element.text().split(" - ").map { it.trim() }
            val name = info.getOrNull(0).orEmpty()
            val parties = info.drop(1).joinToString(" - ")
            PoliticalAuthority(name, parties)
        }
    }
}
