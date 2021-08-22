package com.clockshop.service.handlers;

import com.clockshop.service.MessageTypes;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component(MessageTypes.SHOP)
public class ShopMessageHandler implements TelegramMessageHandler{
   private TelegramBot bot;

    public ShopMessageHandler(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void onMessage(Message message) {
        InlineKeyboardMarkup keyboard=new InlineKeyboardMarkup(
                (new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Марка").callbackData(MessageTypes.STAMP)
                }),
                (new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Материал корпуса").callbackData(MessageTypes.CORP_MATERIAL)
                }),
                (new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Вид измерения").callbackData(MessageTypes.TYPE_OF_MEASUREMENT)
                }),
        (new InlineKeyboardButton[]{
                new InlineKeyboardButton("Назад").callbackData(MessageTypes.HOME)
        }));
        SendMessage request = new SendMessage(message.chat().id(),"Выберете вариант сортировки")
               .replyMarkup(keyboard);
        bot.execute(request);
    }
}
