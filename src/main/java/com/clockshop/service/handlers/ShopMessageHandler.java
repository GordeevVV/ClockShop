package com.clockshop.service.handlers;

import com.clockshop.service.MessageTypes;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component(MessageTypes.SHOP)
public class ShopMessageHandler implements TelegramMessageHandler{
    TelegramBot bot;

    public ShopMessageHandler(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void onMessage(Message message) {
        InlineKeyboardMarkup keyboard=new InlineKeyboardMarkup(
                (new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Марка").callbackData("Марка")
                }),
                (new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Материал корпуса").callbackData("Материал корпуса")
                }),
                (new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Вид измерения").callbackData("Вид измерения")
                }));
        SendMessage request = new SendMessage(message.chat().id(),"Выберете вариант сортировки")
               .replyMarkup(keyboard);
        bot.execute(request);
    }
}
 /*Keyboard keyboard = new ReplyKeyboardMarkup(
                (new KeyboardButton[]{
                        new KeyboardButton("Марка")
                }),
                (new KeyboardButton[]{
                        new KeyboardButton("Материал корпуса")
                }),
                (new KeyboardButton[]{
                        new KeyboardButton("Вид измерения")
                })
        ).oneTimeKeyboard(true).resizeKeyboard(true).selective(true);*/