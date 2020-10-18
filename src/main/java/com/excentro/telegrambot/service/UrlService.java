package com.excentro.telegrambot.service;

import com.excentro.telegrambot.entity.Url;
import com.excentro.telegrambot.repo.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UrlService {
  private final UrlRepository urlRepository;

  @Autowired
  public UrlService(UrlRepository urlRepository) {
    this.urlRepository = urlRepository;
  }

  @Transactional(readOnly = true)
  public List<Url> findIdToNotified() {
    return urlRepository.findByNotifiedIsFalseAndOnlineIsFalseAndLastCheckedIsNotNull();
  }

  @Transactional(readOnly = true)
  public List<Url> findAllUrls(Long chatId) {
    return urlRepository.findByChatId(chatId);
  }

  @Transactional
  public void saveUrls(List<Url> urlList) {
    // TODO сделать проверку существует ли уже такая ссылка
    urlRepository.saveAll(urlList);
  }
}
