package com.clockshop.service.handlers;

import com.pengrad.telegrambot.model.Message;

public interface TelegramMessageHandler {
    void onMessage(Message message);
}
