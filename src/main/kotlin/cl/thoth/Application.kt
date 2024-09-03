package cl.thoth

import cl.thoth.infrastructure.implementation.MunicipalityRepositoryImpl
import cl.thoth.infrastructure.scraper.MunicipalityScraper
import cl.thoth.plugins.configureRouting
import cl.thoth.plugins.configureSerialization
import cl.thoth.service.MunicipalityService
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(
        Netty,
        port = 8080,
        module = Application::module
    )
        .start(wait = true)
}

fun Application.module() {
    val scraper = MunicipalityScraper()
    val repository = MunicipalityRepositoryImpl(scraper)
    val service = MunicipalityService(repository)
    configureSerialization()
    configureRouting(service = service)
}