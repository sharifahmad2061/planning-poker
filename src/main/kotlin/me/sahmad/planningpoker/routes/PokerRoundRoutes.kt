package me.sahmad.planningpoker.routes

import io.ktor.server.application.Application
import io.ktor.server.routing.Route
import io.ktor.server.routing.routing
import io.ktor.server.websocket.receiveDeserialized
import io.ktor.server.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import me.sahmad.planningpoker.models.CreatePokerRound
import me.sahmad.planningpoker.models.SessionStorage

fun Application.createPokerRoundRoutes() {
    routing {
        pokerRoundRoutes()
     }
}

fun Route.pokerRoundRoutes() {
    webSocket("/ws/createPokerRound") {
        send(Frame.Text("you are connected to the server"))
        val request = receiveDeserialized<CreatePokerRound>()
        val sessionId = request.sessionId
        SessionStorage.sessions[sessionId]?.let { session ->
            session.webSocketSessions += this
        }
    }
}