package cl.thoth.service

import cl.thoth.domain.model.Municipality
import cl.thoth.domain.model.MunicipalityDetails
import cl.thoth.domain.repository.MunicipalityRepository

class MunicipalityService(private val repository: MunicipalityRepository) {
    suspend fun getMunicipalities(): List<Municipality> {
        return repository.getMunicipalities()
    }

    suspend fun getMunicipalityDetails(id: String): MunicipalityDetails {
        return repository.getMunicipalityDetails(id)
    }
}