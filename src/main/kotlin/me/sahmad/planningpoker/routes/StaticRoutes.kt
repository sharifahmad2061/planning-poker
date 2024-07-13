package me.sahmad.planningpoker.routes

import io.ktor.server.application.Application
import io.ktor.server.http.content.CompressedFileType
import io.ktor.server.http.content.staticResources
import io.ktor.server.routing.routing

fun Application.createStaticRoutes() {
    routing {
        staticResources("/static", "static") {
            preCompressed(CompressedFileType.GZIP)
        }
    }
}