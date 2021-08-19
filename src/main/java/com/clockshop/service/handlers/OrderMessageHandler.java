package com.clockshop.service.handlers;

import com.clockshop.service.MessageTypes;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import org.springframework.stereotype.Component;

@Component(MessageTypes.ORDER)
public class OrderMessageHandler implements TelegramCallbackQueryHandler{
    TelegramBot bot;

    public OrderMessageHandler(TelegramBot bot) {
        this.bot = bot;
    }


    @Override
    public void onCallBackQuery(CallbackQuery callbackQuery) {

    }
}
