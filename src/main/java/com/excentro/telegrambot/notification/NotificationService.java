package com.excentro.telegrambot.notification;

import com.excentro.telegrambot.entity.Url;
import com.excentro.telegrambot.service.UrlService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationService {
  private final UrlService urlService;

  public NotificationService(UrlService urlService) {
    this.urlService = urlService;
  }

  @Scheduled(fixedDelay = 60000)
  public void sendNotifications() {
    List<Url> idToNotified = urlService.findIdToNotified();
    for (Url url : idToNotified) {
      System.out.println(url.getId());
    }
  }
}
