package me.sahmad.planningpoker.routes

import io.ktor.server.application.Application
import io.ktor.server.routing.Route
import io.ktor.server.routing.routing
import io.ktor.server.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.readText

fun Application.createPokerRoundRoutes() {
    routing {
        pokerRoundRoutes()
     }
}

fun Route.pokerRoundRoutes() {
    webSocket("/ws") {
        send(Frame.Text("you are connected to the server"))
        for (frame in incoming) {
            frame as? Frame.Text ?: continue
            val receivedText = frame.readText()
            println(receivedText)
        }
    }
}