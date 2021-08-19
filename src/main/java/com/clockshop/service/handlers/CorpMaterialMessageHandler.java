package com.clockshop.service.handlers;

import com.clockshop.service.MessageTypes;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component(MessageTypes.CORP_MATERIAL)
public class CorpMaterialMessageHandler implements TelegramMessageHandler{
    TelegramBot bot;

    public CorpMaterialMessageHandler(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void onMessage(Message message) {
        InlineKeyboardMarkup keyboard=new InlineKeyboardMarkup(
                (new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Пластиковые").callbackData("Пластик"),
                        new InlineKeyboardButton("Аллойные").callbackData("Алой")
                }),
                (new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Золотые").callbackData("Золото"),
                        new InlineKeyboardButton("Латунные").callbackData("Латунь"),

                }),
                (new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Стальные").callbackData("Сталь"),
                        new InlineKeyboardButton("Титановые").callbackData("Титан")
                }),
                (new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Назад").callbackData("Назад1")
                }));
        DeleteMessage deleteMessage=new DeleteMessage(message.chat().id(),message.messageId());
        bot.execute(deleteMessage);
        SendMessage request = new SendMessage(message.chat().id(),"Доступные материалы корпуса")
                .replyMarkup(keyboard);
        bot.execute(request);
    }
}
