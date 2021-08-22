package com.clockshop.service.handlers;

import com.clockshop.service.MessageTypes;
import com.clockshop.service.entity.Stamp;
import com.clockshop.service.repository.StampRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component(MessageTypes.STAMP)
public class StampMessageHandler implements TelegramCallbackQueryHandler{
    private TelegramBot bot;
    private StampRepository stampRepository;
    public StampMessageHandler(TelegramBot bot,StampRepository stampRepository) {
        this.stampRepository=stampRepository;
        this.bot = bot;
    }

    @Override
    public void onCallBackQuery(CallbackQuery callbackQuery) {
        List<InlineKeyboardButton> listButtons=new ArrayList<>();
        List<InlineKeyboardButton[]>listRows=new ArrayList<>();
        int i=0;
        for (Stamp stamp: stampRepository.getList()) {
            listButtons.add(new InlineKeyboardButton(stamp.getStamp()).callbackData(stamp.getStamp()));
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
                , "Доступные марки:")
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true);
            request.replyMarkup(new InlineKeyboardMarkup(listRows.toArray(new InlineKeyboardButton[0][0])));
        listRows.clear();
//        SendMessage request = new SendMessage(callbackQuery.message().chat().id(),"Доступные марки")
//                .replyMarkup(new InlineKeyboardMarkup(listRows.toArray(new InlineKeyboardButton[0][0])));
//        listRows.clear();
        bot.execute(request);
    }
}
