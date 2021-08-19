package com.clockshop.service.handlers;

import com.clockshop.service.MessageTypes;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component(MessageTypes.TYPE_OF_MEASUREMENT)
public class TypeOfMeasurementMessageHandler implements TelegramMessageHandler{
    TelegramBot bot;

    public TypeOfMeasurementMessageHandler(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void onMessage(Message message) {
        InlineKeyboardMarkup keyboard=new InlineKeyboardMarkup(
                (new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Электронные").callbackData("Электронные"),
                        new InlineKeyboardButton("Кварцевые").callbackData("Кварцевые")
                }),
                (new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Механические").callbackData("Механические"),
                        new InlineKeyboardButton("Гибридные").callbackData("Гибридные"),

                }),
                (new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Назад").callbackData("Назад1")
                }));
        DeleteMessage deleteMessage=new DeleteMessage(message.chat().id(),message.messageId());
        bot.execute(deleteMessage);
        SendMessage request = new SendMessage(message.chat().id(),"Доступные типы механизма")
                .replyMarkup(keyboard);
        bot.execute(request);
    }
}
