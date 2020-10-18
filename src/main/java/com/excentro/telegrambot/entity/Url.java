package com.excentro.telegrambot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Url.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "url",
    indexes = {@Index(name = "chat_id_idx", columnList = "chatId"),
        @Index(name = "id_idx", columnList = "id"),
        @Index(name = "online_id_idx", columnList = "online"),
        @Index(name = "notified_id_idx", columnList = "notified")})
public class Url {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String href;
  private boolean online = false;
  private boolean notified = false;
  private LocalDateTime lastChecked;
  private LocalDateTime lastOnline;
  private Long chatId;

  public Url(String href, Long chatId) {
    this.href = href;
    this.chatId = chatId;
  }

  @Override
  public String toString() {
    return "Url{" + "href='" + href + '\'' + '}';
  }
}
