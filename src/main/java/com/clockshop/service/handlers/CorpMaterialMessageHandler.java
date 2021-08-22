package com.clockshop.service.handlers;

import com.clockshop.service.MessageTypes;
import com.clockshop.service.entity.Material;
import com.clockshop.service.entity.Stamp;
import com.clockshop.service.repository.MaterialRepository;
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

@Component(MessageTypes.CORP_MATERIAL)
public class CorpMaterialMessageHandler implements TelegramCallbackQueryHandler{
    TelegramBot bot;
    MaterialRepository materialRepository;

    public CorpMaterialMessageHandler(TelegramBot bot,MaterialRepository materialRepository) {
        this.bot = bot;
        this.materialRepository=materialRepository;
    }

    @Override
    public void onCallBackQuery(CallbackQuery callbackQuery) {
        List<InlineKeyboardButton> listButtons=new ArrayList<>();
        List<InlineKeyboardButton[]>listRows=new ArrayList<>();
        int i=0;
        for (Material material: materialRepository.getList()) {
            listButtons.add(new InlineKeyboardButton(material.getMaterial()).callbackData(material.getMaterial()));
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
//                        new InlineKeyboardButton("Пластиковые").callbackData("Пластик"),
//                        new InlineKeyboardButton("Аллойные").callbackData("Алой")
//                }),
//                (new InlineKeyboardButton[]{
//                        new InlineKeyboardButton("Золотые").callbackData("Золото"),
//                        new InlineKeyboardButton("Латунные").callbackData("Латунь"),
//
//                }),
//                (new InlineKeyboardButton[]{
//                        new InlineKeyboardButton("Стальные").callbackData("Сталь"),
//                        new InlineKeyboardButton("Титановые").callbackData("Титан")
//                }),
//                (new InlineKeyboardButton[]{
//                        new InlineKeyboardButton("Назад").callbackData("Назад1")
//                }));
        EditMessageText request = new EditMessageText(callbackQuery.message().chat().id(), callbackQuery.message().messageId()
                , "Доступные материалы корпуса:")
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true);
        request.replyMarkup(new InlineKeyboardMarkup(listRows.toArray(new InlineKeyboardButton[0][0])));
        listRows.clear();
        bot.execute(request);
    }
}
