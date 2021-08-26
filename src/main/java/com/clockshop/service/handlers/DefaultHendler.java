package com.clockshop.service.handlers;

import com.clockshop.service.MessageTypes;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component(MessageTypes.DEFAULT)
public class DefaultHendler implements TelegramMessageHandler{
    static Logger LOGGER= LoggerFactory.getLogger(JdbsHandler.class);
    TelegramBot bot;

    public DefaultHendler(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void onMessage(Message message) {
        SendMessage sendMessage=new SendMessage(message.chat().id(),"Default");
        bot.execute(sendMessage);
        LOGGER.warn("Cant parse message or callback");
    }

}
