package com.clockshop.service.handlers;

import com.clockshop.service.constants.MessageTypes;
import com.clockshop.service.entity.Material;
import com.clockshop.service.repository.MaterialJpaRepository;
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

@Component(MessageTypes.CORP_MATERIAL)

public class CorpMaterialMessageHandler implements TelegramCallbackQueryHandler{
    TelegramBot bot;
    MaterialJpaRepository materialJpaRepository;
    public CorpMaterialMessageHandler(@Qualifier("ShopBot") TelegramBot bot, MaterialJpaRepository materialJpaRepository) {
        this.bot = bot;
        this.materialJpaRepository=materialJpaRepository;
    }

    @Override
    public void onCallBackQuery(CallbackQuery callbackQuery) {
        List<InlineKeyboardButton> listButtons=new ArrayList<>();
        List<InlineKeyboardButton[]>listRows=new ArrayList<>();
        int i=0;
        for (Material material: materialJpaRepository.findAll()) {
            listButtons.add(new InlineKeyboardButton(material.getMaterial()).callbackData(MessageTypes.BUY_MATERIAL+"_"+material.getMaterialId()));
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
                , "Доступные материалы корпуса:")
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true);
        request.replyMarkup(new InlineKeyboardMarkup(listRows.toArray(new InlineKeyboardButton[0][0])));
        listRows.clear();
        bot.execute(request);
    }
}
