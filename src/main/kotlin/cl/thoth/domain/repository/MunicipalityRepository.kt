package cl.thoth.domain.repository

import cl.thoth.domain.model.Municipality
import cl.thoth.domain.model.MunicipalityDetails

interface MunicipalityRepository {
    suspend fun getMunicipalities(): List<Municipality>
    suspend fun getMunicipalityDetails(id: String): MunicipalityDetails
}