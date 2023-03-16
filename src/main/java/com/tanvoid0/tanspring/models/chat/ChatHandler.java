package com.tanvoid0.tanspring.models.chat;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
public class ChatHandler extends TextWebSocketHandler {

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    // Handle incoming message
    final String payload = message.getPayload();
    log.info("Incoming payload: {}", payload);

    // Broadcast message to all connected clients
    session.sendMessage(new TextMessage("Hello " + payload));
  }
}
