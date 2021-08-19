package com.clockshop.service.handlers;

import com.clockshop.service.MessageTypes;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component(MessageTypes.STAMP)
public class StampMessageHandler implements TelegramMessageHandler{
    TelegramBot bot;

    public StampMessageHandler(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void onMessage(Message message) {
        InlineKeyboardMarkup keyboard=new InlineKeyboardMarkup(
                (new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Casio").callbackData("Casio"),
                        new InlineKeyboardButton("Seiko").callbackData("Seiko")
                }),
                (new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Tag Heuer").callbackData("Tag Heuer"),
                        new InlineKeyboardButton("Omega").callbackData("Omega"),

                }),
                (new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Timex").callbackData("Timex"),
                        new InlineKeyboardButton("Citizen").callbackData("Citizen")
                }),
                (new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Titan").callbackData("Titan"),
                        new InlineKeyboardButton("Patek Philippe").callbackData("Patek Philippe")
                }),
                (new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Oakley").callbackData("Oakley"),
                        new InlineKeyboardButton("Rolex").callbackData("Rolex")
                }),
                (new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Назад").callbackData("Назад1")
                }));
        DeleteMessage deleteMessage=new DeleteMessage(message.chat().id(),message.messageId());
        bot.execute(deleteMessage);
        SendMessage request = new SendMessage(message.chat().id(),"Доступные марки")
             .replyMarkup(keyboard);
        bot.execute(request);
    }
}
