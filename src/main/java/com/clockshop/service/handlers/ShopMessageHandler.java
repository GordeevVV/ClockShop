package com.clockshop.service.handlers;

import com.clockshop.service.constants.MessageTypes;
import com.clockshop.service.constants.TelegramBotButtons;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(MessageTypes.SHOP)
public class ShopMessageHandler implements TelegramMessageHandler,TelegramCallbackQueryHandler{
   private TelegramBot bot;

    public ShopMessageHandler(@Qualifier("ShopBot") TelegramBot bot) {
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
        InlineKeyboardMarkup keyboard=new InlineKeyboardMarkup(
                (new InlineKeyboardButton[]{
                        new InlineKeyboardButton(TelegramBotButtons.Marka).callbackData(MessageTypes.STAMP)
                }),
                (new InlineKeyboardButton[]{
                        new InlineKeyboardButton(TelegramBotButtons.MaterialKorusa).callbackData(MessageTypes.CORP_MATERIAL)
                }),
                (new InlineKeyboardButton[]{
                        new InlineKeyboardButton(TelegramBotButtons.VidIzmerenia).callbackData(MessageTypes.TYPE_OF_MEASUREMENT)
                }),
                (new InlineKeyboardButton[]{
                        new InlineKeyboardButton(TelegramBotButtons.Nazad).callbackData(MessageTypes.HOME)
                }));
        SendMessage request = new SendMessage(message.chat().id(),"Выберете вариант сортировки")
                .replyMarkup(keyboard);
        bot.execute(request);
    }
}
