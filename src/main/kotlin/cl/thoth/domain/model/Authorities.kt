package cl.thoth.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Authorities(
    val mayor: PoliticalAuthority,
    val mayorPhoto: String,
    val councilors: List<PoliticalAuthority>,
    val senators: List<PoliticalAuthority>,
    val deputies: List<PoliticalAuthority>,
    val regionalGovernor: String,
    val cores: List<PoliticalAuthority>
)

@Serializable
data class PoliticalAuthority(
    val name: String, val politicalParty: String
)