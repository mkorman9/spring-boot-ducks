package com.github.mkorman9

import org.slf4j.LoggerFactory
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class QuackingWebSocket(
    private val jwtDecoder: JwtDecoder
) : TextWebSocketHandler() {
    private val log = LoggerFactory.getLogger(QuackingWebSocket::class.java)

    override fun afterConnectionEstablished(session: WebSocketSession) {
        super.afterConnectionEstablished(session)
        log.info("New client connected: ${session.id}")
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        super.afterConnectionClosed(session, status)
        log.info("Client disconnected: ${session.id}")
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val token = try {
            jwtDecoder.decode(message.payload)
        } catch (e: Exception) {
            log.info("Invalid token received from: ${session.id}")
            return
        }

        log.info("Token of ${token.subject} received from: ${session.id}")

        session.sendMessage(TextMessage("Quack!"))
    }
}
