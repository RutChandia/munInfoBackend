package cl.thoth.infrastructure.scraper

import cl.thoth.domain.model.Municipality
import cl.thoth.domain.model.MunicipalityDetails
import io.ktor.utils.io.errors.*
import org.jsoup.Connection
import org.jsoup.Jsoup

class MunicipalityScraper {
    fun scrapeMunicipalityName(url: String): List<Municipality> {
        return try {
            val doc = connect(url).get()
            doc.select("select[name=municipio] option[value]").mapNotNull { option ->
                val id = option.attr("value")
                val name = option.text().split(" - ").firstOrNull() ?: return@mapNotNull null
                Municipality(id, name)
            }
        } catch (ioe: IOException) {
            ioe.printStackTrace()
            emptyList()
        }
    }

    fun getMunicipalityDetails(id: String): MunicipalityDetails {
        val url = "https://datos.sinim.gov.cl/ficha_comunal.php"
        val doc = connect(url).data("municipio", id).post()

        return MunicipalityDetails(
            municipalityName = DocumentUtils.extractText(doc, "h3.tit_comuna"),
            region = DocumentUtils.extractData(doc, "Región:"),
            province = DocumentUtils.extractData(doc, "Provincia:"),
            rol = DocumentUtils.extractData(doc, "Rut:"),
            address = DocumentUtils.extractData(doc, "Dirección:"),
            phone = DocumentUtils.extractData(doc, "Teléfono:"),
            fax = DocumentUtils.extractData(doc, "Fax:"),
            web = DocumentUtils.extractData(doc, "Web:", "href"),
            email = DocumentUtils.extractData(doc, "Email:", "href")?.replace("mailto:", ""),
            imageUrl = DocumentUtils.extractImageUrl(doc, "div.imagen_municipio img"),
            authorities = AuthorityUtils.extractAuthorities(doc)
        )
    }

    private fun connect(url: String): Connection {
        return Jsoup.connect(url).apply {
            userAgent("Mozilla/5.0 (X11; Linux x86_64; rv:127.0) Gecko/20100101 Firefox/127.0")
        }
    }
}