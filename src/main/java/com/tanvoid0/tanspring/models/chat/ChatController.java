package com.tanvoid0.tanspring.models.chat;

import lombok.extern.slf4j.Slf4j;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class ChatController {

  @MessageMapping("/chat")
  @SendTo("/topic/chat")
  public ChatMessage sendMessage(@Payload final ChatMessage message) {
    log.info("Sending chat with payload {}", message);
    return message;
  }
}

/**
 * var socket = new WebSocket("ws://localhost:8080/chat");
 * <p>
 * socket.onmessage = function(event) {
 * var message = JSON.parse(event.data);
 * // Handle incoming message
 * };
 * <p>
 * socket.send(JSON.stringify({text: "Hello World!"}));
 */
