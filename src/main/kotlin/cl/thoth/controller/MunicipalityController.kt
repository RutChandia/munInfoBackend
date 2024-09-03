package cl.thoth.controller

import cl.thoth.domain.model.Municipality
import cl.thoth.service.MunicipalityService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class MunicipalityController(private val service: MunicipalityService) {
    fun Route.municipalityRoutes() {
        route("/") {
            get {
                val municipalities: List<Municipality> = service.getMunicipalities()
                call.respond(municipalities)
            }
        }
        route("/township/{id}") {
            get {
                val municipalityId = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
                val municipalityDetails = service.getMunicipalityDetails(municipalityId)
                call.respond(municipalityDetails)
            }
        }
    }
}