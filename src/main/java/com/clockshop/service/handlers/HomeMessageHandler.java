package com.clockshop.service.handlers;

import com.clockshop.service.constants.MessageTypes;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(MessageTypes.HOME)
public class HomeMessageHandler implements TelegramMessageHandler,TelegramCallbackQueryHandler{
    TelegramBot bot;

    public HomeMessageHandler(@Qualifier("ShopBot") TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void onMessage(Message message) {
       screenVision(message);
    }

    @Override
    public void onCallBackQuery(CallbackQuery callbackQuery) {
        screenVision(callbackQuery.message());
    }
    private void screenVision(Message message){
        Keyboard keyboard = new ReplyKeyboardMarkup(
                (new KeyboardButton[]{
                        new KeyboardButton("Магазин"),
                        new KeyboardButton("Корзина")
                }),
                (new KeyboardButton[]{
                        new KeyboardButton("Заказы"),
                        new KeyboardButton("Новости")
                }),
                (new KeyboardButton[]{
                        new KeyboardButton("Помощь"),
                        new KeyboardButton("Настройки")
                })
        ).oneTimeKeyboard(false).resizeKeyboard(true).selective(true);
        SendMessage request = new SendMessage(message.chat().id(), "Добро пожаловать в магазин часов")
                .replyMarkup(keyboard);
        bot.execute(request);
    }

}
