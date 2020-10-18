package com.excentro.telegrambot.repo;

import com.excentro.telegrambot.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UrlRepository extends JpaRepository<Url, Long> {
  List<Url> findByNotifiedIsFalseAndOnlineIsFalseAndLastCheckedIsNotNull();

  List<Url> findByChatId(Long chatId);
}
