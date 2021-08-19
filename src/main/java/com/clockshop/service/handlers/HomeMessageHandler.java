package com.clockshop.service.handlers;

import com.clockshop.service.MessageTypes;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component(MessageTypes.HOME)
public class HomeMessageHandler implements TelegramMessageHandler {
    TelegramBot bot;

    public HomeMessageHandler(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void onMessage(Message message) {
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
