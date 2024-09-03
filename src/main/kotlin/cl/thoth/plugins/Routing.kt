package cl.thoth.plugins

import cl.thoth.controller.MunicipalityController
import cl.thoth.service.MunicipalityService
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(service: MunicipalityService) {
    routing {
        MunicipalityController(service).apply { municipalityRoutes() }
    }
}
