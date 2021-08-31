package com.clockshop.service.listeners;

import com.clockshop.service.constants.MessageTypes;
import com.clockshop.service.constants.TelegramBotButtons;
import com.clockshop.service.handlers.TelegramCallbackQueryHandler;
import com.clockshop.service.handlers.TelegramMessageHandler;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Component
public class ClockUpdateListener implements UpdatesListener {
    private Map<String, TelegramMessageHandler> telegramMessageHandlerMap;
    private Map<String, TelegramCallbackQueryHandler> telegramCallbackQueryHandlerMap;
    private TelegramBot bot;

    public ClockUpdateListener(Map<String, TelegramMessageHandler> map, @Qualifier("ShopBot") TelegramBot bot, Map<String, TelegramCallbackQueryHandler> map1) {
        this.telegramMessageHandlerMap = map;
        this.bot = bot;
        this.telegramCallbackQueryHandlerMap = map1;
    }

    @PostConstruct
    public void postConstruct() {
        bot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> list) {

        for (Update update : list) {
            if (update.message() != null)
                telegramMessageHandlerMap.get(getMessageType(update)).onMessage(update.message());
            else if (update.callbackQuery() != null)
                telegramCallbackQueryHandlerMap.get(getMessageType(update)).onCallBackQuery(update.callbackQuery());
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    public String getMessageType(Update update) {
        String data = null;
        if (update.message() != null) {
            data = update.message().text();
        }
        if (update.callbackQuery() != null) {
            data = update.callbackQuery().data();
        }
        if (data != null) {
            if (data.equals("/start") || data.equals(TelegramBotButtons.Domoi))
                return MessageTypes.HOME;
            if (data.equals(TelegramBotButtons.Magazin))
                return MessageTypes.SHOP;
            if (data.equals(TelegramBotButtons.Korzina))
                return MessageTypes.BASKET;
            if (data.equals(TelegramBotButtons.Zakazy))
                return MessageTypes.ORDER;
            if (data.equalsIgnoreCase(MessageTypes.STAMP))
                return MessageTypes.STAMP;
            if (data.equalsIgnoreCase(MessageTypes.CORP_MATERIAL))
                return MessageTypes.CORP_MATERIAL;
            if (data.equalsIgnoreCase(MessageTypes.TYPE_OF_MEASUREMENT))
                return MessageTypes.TYPE_OF_MEASUREMENT;
            if (data.equalsIgnoreCase(MessageTypes.SHOP))
                return MessageTypes.SHOP;
            if (data.equalsIgnoreCase(MessageTypes.HOME))
                return MessageTypes.HOME;
            if (data.matches("^" + MessageTypes.BUY_STAMP + "_[0-9]+$"))
                return MessageTypes.BUY_STAMP;
            if (data.matches("^" + MessageTypes.BUY_MATERIAL + "_[0-9]+$"))
                return MessageTypes.BUY_MATERIAL;
            if (data.matches("^" + MessageTypes.BUY_TYPE_OF_MEASUREMENT + "_[0-9]+$"))
                return MessageTypes.BUY_TYPE_OF_MEASUREMENT;
            if (data.matches("^" + MessageTypes.BUY_CONFIRMED + "_[0-9]+$"))
                return MessageTypes.BUY_CONFIRMED;
            if (data.matches("^Delete_[0-9]+$"))
                return MessageTypes.DELETE;
            if (data.matches("^[0-9]+$"))
                return MessageTypes.BASKET;
            if (data.equals(MessageTypes.ORDER))
                return MessageTypes.ORDER;
        }
        return MessageTypes.DEFAULT;
    }
}