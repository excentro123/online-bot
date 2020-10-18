package com.excentro.telegrambot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.excentro.telegrambot.entity.Chat;
import com.excentro.telegrambot.entity.Url;
import com.excentro.telegrambot.service.ChatService;
import com.excentro.telegrambot.service.UrlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@PropertySource("classpath:telegram.properties")
public class OnlineCheckBot extends TelegramLongPollingBot {

  private final ChatService chatService;
  private final UrlService urlService;

  @Value("${bot.name}")
  private String botName;

  @Value("${bot.token}")
  private String token;

  public OnlineCheckBot(ChatService chatService, UrlService urlService) {
    this.chatService = chatService;
    this.urlService = urlService;
  }

  @Override
  public void onUpdateReceived(Update update) {
    SendMessage message;
    Long chatId = update.getMessage().getChatId();

    if (!update.hasMessage() || !update.getMessage().hasText()) {
      return;
    }

    String messageText = update.getMessage().getText();

    if (messageText.startsWith("/list")) {
      message = new SendMessage() // Create a SendMessage object with mandatory fields
          .setChatId(chatId).setText(String.valueOf(urlService.findAllUrls(chatId)));
    } else if (messageText.startsWith("/add")) {
      List<String> stringList = new ArrayList<>(Arrays.asList(messageText.split("\\s+")));
      stringList.remove(0); // removing /add command
      List<Url> urls = strToUrls(chatId, stringList);

      Chat chat = new Chat(chatId);
      chatService.saveChat(chat);
      urlService.saveUrls(urls);

      message =
          new SendMessage().setChatId(chatId).setText("adding " + stringList + " for monitoring");
    } else {
      message = new SendMessage().setChatId(chatId).setText("type /help to get all commands");
    }

    try {
      execute(message); // Call method to send the message
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }

  private List<Url> strToUrls(Long chatId, List<String> stringList) {
    List<Url> urls = new ArrayList<>();
    for (String s : stringList) {
      urls.add(new Url(s, chatId));
    }
    return urls;
  }

  @Override
  public String getBotUsername() {
    return botName;
  }

  @Override
  public String getBotToken() {
    return token;
  }
}
