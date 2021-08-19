package com.clockshop.service.handlers;

import com.clockshop.service.MessageTypes;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import org.springframework.stereotype.Component;

@Component(MessageTypes.BASKET)
public class BasketMessageHandler implements TelegramMessageHandler{
    TelegramBot bot;

    public BasketMessageHandler(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void onMessage(Message message) {

    }
}
