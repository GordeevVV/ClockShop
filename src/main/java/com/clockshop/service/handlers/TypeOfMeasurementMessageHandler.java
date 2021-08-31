package com.clockshop.service.handlers;

import com.clockshop.service.constants.MessageTypes;
import com.clockshop.service.entity.MechType;
import com.clockshop.service.repository.MechTypeJpaRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.EditMessageText;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component(MessageTypes.TYPE_OF_MEASUREMENT)
public class TypeOfMeasurementMessageHandler implements TelegramCallbackQueryHandler{
    TelegramBot bot;
    MechTypeJpaRepository mechTypeJpaRepository;

    public TypeOfMeasurementMessageHandler(@Qualifier("ShopBot") TelegramBot bot, MechTypeJpaRepository mechTypeJpaRepository) {
        this.bot = bot;
        this.mechTypeJpaRepository=mechTypeJpaRepository;
    }

    @Override
    public void onCallBackQuery(CallbackQuery callbackQuery) {
        List<InlineKeyboardButton> listButtons=new ArrayList<>();
        List<InlineKeyboardButton[]>listRows=new ArrayList<>();
        int i=0;
        for (MechType mechType: mechTypeJpaRepository.findAll()) {
            listButtons.add(new InlineKeyboardButton
                    (mechType.getType()).callbackData(MessageTypes.BUY_TYPE_OF_MEASUREMENT+"_"+mechType.getMechId()));
            if(i%2==1) {
                listRows.add(listButtons.toArray(new InlineKeyboardButton[0]));
                listButtons.clear();
            }
            i++;
        }
        listButtons.add(new InlineKeyboardButton("Назад").callbackData(MessageTypes.SHOP));
        listRows.add(listButtons.toArray(new InlineKeyboardButton[0]));
        listButtons.clear();
        EditMessageText request = new EditMessageText(callbackQuery.message().chat().id(), callbackQuery.message().messageId()
                , "Доступные типы механизмов:")
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true);
        request.replyMarkup(new InlineKeyboardMarkup(listRows.toArray(new InlineKeyboardButton[0][0])));
        listRows.clear();
        bot.execute(request);
    }
}
