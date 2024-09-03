package cl.thoth.infrastructure.implementation

import cl.thoth.domain.model.Municipality
import cl.thoth.domain.model.MunicipalityDetails
import cl.thoth.domain.repository.MunicipalityRepository
import cl.thoth.infrastructure.scraper.MunicipalityScraper

class MunicipalityRepositoryImpl(private val scraper: MunicipalityScraper): MunicipalityRepository {
    private val url = "https://datos.sinim.gov.cl/ficha_comunal.php"

    override suspend fun getMunicipalities(): List<Municipality> {
        return scraper.scrapeMunicipalityName(url)
    }

    override suspend fun getMunicipalityDetails(id: String): MunicipalityDetails {
        return scraper.getMunicipalityDetails(id = id)
    }

}