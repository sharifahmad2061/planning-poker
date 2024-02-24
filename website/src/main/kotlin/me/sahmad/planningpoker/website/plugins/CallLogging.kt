package me.sahmad.planningpoker.website.plugins

import io.ktor.server.application.Applicationimport io.ktor.server.application.install
import io.ktor.server.plugins.callloging.CallLogging

fun Application.configureCallLogging() {
    install(CallLogging)
}