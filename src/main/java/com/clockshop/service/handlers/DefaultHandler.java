package com.clockshop.service.handlers;

import com.clockshop.service.constants.MessageTypes;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(MessageTypes.DEFAULT)
public class DefaultHandler implements TelegramMessageHandler, TelegramCallbackQueryHandler{
    static Logger LOGGER= LoggerFactory.getLogger(JdbsHandler.class);
    TelegramBot bot;

    public DefaultHandler(@Qualifier("ShopBot") TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void onMessage(Message message) {
        SendMessage sendMessage=new SendMessage(message.chat().id(),"Не удалось выполнить действие. Попробуйте позже...");
        bot.execute(sendMessage);
        LOGGER.warn("Cant parse message");
    }

    @Override
    public void onCallBackQuery(CallbackQuery callbackQuery) {
        SendMessage sendMessage=new SendMessage(callbackQuery.message().chat().id(),"Не удалось выполнить действие. Попробуйте позже...");
        bot.execute(sendMessage);
        LOGGER.warn("Cant parse callback");
    }
}
