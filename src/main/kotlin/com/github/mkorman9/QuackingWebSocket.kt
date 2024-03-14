package com.github.mkorman9

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class QuackingWebSocket : TextWebSocketHandler() {
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
        log.info("Received '${message.payload}' from ${session.id}")
        session.sendMessage(TextMessage("Quack!"))
    }
}
