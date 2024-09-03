package cl.thoth.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MunicipalityDetails(
    val municipalityName: String,
    val region: String?,
    val province: String?,
    val rol: String?,
    val address: String?,
    val phone: String?,
    val fax: String?,
    val web: String?,
    val email: String?,
    val imageUrl: String,
    val authorities: Authorities
)
