package com.tanvoid0.tanspring.models.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {

  private Long senderId;
  private Long receiverId;

  private String content;

  private LocalDateTime timestamp;
}
