package com.excentro.telegrambot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "chat", indexes = {@Index(name = "id_idx", columnList = "id"),
    @Index(name = "chat_id_idx", columnList = "chatId")})
public class Chat {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private Long chatId;
  private Integer stateId;

  public Chat(Long chatId) {
    this.chatId = chatId;
  }
}
