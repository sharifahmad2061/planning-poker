package me.sahmad.planningpoker.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.pebble.Pebble
import io.pebbletemplates.pebble.loader.ClasspathLoader

fun Application.configureTemplating() {
    install(Pebble){
        loader(ClasspathLoader().apply{
            prefix = "templates"
        })
    }
}