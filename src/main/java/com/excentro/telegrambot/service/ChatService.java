package com.excentro.telegrambot.service;

import com.excentro.telegrambot.entity.Chat;
import com.excentro.telegrambot.repo.ChatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatService {
  private final ChatRepository chatRepository;

  public ChatService(ChatRepository chatRepository) {
    this.chatRepository = chatRepository;
  }

  @Transactional
  public void saveChat(Chat chat) {
    chatRepository.save(chat);
  }

  @Transactional(readOnly = true)
  public Iterable<Chat> findAllChats() {
    return chatRepository.findAll();
  }
}
