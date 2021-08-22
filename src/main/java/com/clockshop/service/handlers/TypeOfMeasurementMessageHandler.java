package com.clockshop.service.handlers;

import com.clockshop.service.MessageTypes;
import com.clockshop.service.entity.Material;
import com.clockshop.service.entity.MechType;
import com.clockshop.service.repository.MechTypeRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component(MessageTypes.TYPE_OF_MEASUREMENT)
public class TypeOfMeasurementMessageHandler implements TelegramCallbackQueryHandler{
    TelegramBot bot;
    MechTypeRepository mechTypeRepository;

    public TypeOfMeasurementMessageHandler(TelegramBot bot,MechTypeRepository mechTypeRepository) {
        this.bot = bot;
        this.mechTypeRepository=mechTypeRepository;
    }

    @Override
    public void onCallBackQuery(CallbackQuery callbackQuery) {
        List<InlineKeyboardButton> listButtons=new ArrayList<>();
        List<InlineKeyboardButton[]>listRows=new ArrayList<>();
        int i=0;
        for (MechType mechType: mechTypeRepository.getList()) {
            listButtons.add(new InlineKeyboardButton(mechType.getType()).callbackData(mechType.getType()));
            if(i%2==1) {
                listRows.add(listButtons.toArray(new InlineKeyboardButton[0]));
                listButtons.clear();
            }
            i++;
        }
        listButtons.add(new InlineKeyboardButton("Назад").callbackData(MessageTypes.SHOP));
        listRows.add(listButtons.toArray(new InlineKeyboardButton[0]));
        listButtons.clear();
//        InlineKeyboardMarkup keyboard=new InlineKeyboardMarkup(
//                (new InlineKeyboardButton[]{
//                        new InlineKeyboardButton("Электронные").callbackData("Электронные"),
//                        new InlineKeyboardButton("Кварцевые").callbackData("Кварцевые")
//                }),
//                (new InlineKeyboardButton[]{
//                        new InlineKeyboardButton("Механические").callbackData("Механические"),
//                        new InlineKeyboardButton("Гибридные").callbackData("Гибридные"),
//
//                }),
//                (new InlineKeyboardButton[]{
//                        new InlineKeyboardButton("Назад").callbackData(MessageTypes.SHOP)
//                }));
        EditMessageText request = new EditMessageText(callbackQuery.message().chat().id(), callbackQuery.message().messageId()
                , "Доступные типы механизмов:")
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true);
        request.replyMarkup(new InlineKeyboardMarkup(listRows.toArray(new InlineKeyboardButton[0][0])));
        listRows.clear();
//        SendMessage request = new SendMessage(callbackQuery.message().chat().id(),"Доступные типы механизма")
//                .replyMarkup(keyboard);
        bot.execute(request);
    }
}
