package com.clockshop.service.handlers;

import com.clockshop.service.MessageTypes;
import com.clockshop.service.entity.Material;
import com.clockshop.service.entity.MechType;
import com.clockshop.service.entity.Product;
import com.clockshop.service.entity.Stamp;
import com.clockshop.service.repository.*;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JdbsHandler{
    static Logger LOGGER= LoggerFactory.getLogger(JdbsHandler.class);
    TelegramBot bot;
    StampJpaRepository stampJpaRepository;
    MaterialJpaRepository materialJpaRepository;
    MechTypeJpaRepository mechTypeJpaRepository;
    ProductJpaRepository productJpaRepository;

    public JdbsHandler(TelegramBot bot, StampJpaRepository stampJpaRepository, MaterialJpaRepository materialJpaRepository
            , MechTypeJpaRepository mechTypeJpaRepository, ProductJpaRepository productJpaRepository) {
        this.bot = bot;
        this.stampJpaRepository = stampJpaRepository;
        this.materialJpaRepository = materialJpaRepository;
        this.mechTypeJpaRepository = mechTypeJpaRepository;
        this.productJpaRepository = productJpaRepository;
    }

    public void clockDeployment(CallbackQuery callbackQuery,List<Product> list, String typeOfBack) {
        int count=0;
        List<InlineKeyboardButton>inlineKeyboardButtons=new ArrayList<>();
        for (Product product:list) {
            inlineKeyboardButtons.add(new InlineKeyboardButton("Купить 1 шт "+product.getPrice()+" руб")
                    .callbackData(Integer.toString(product.getProductId())));
            SendPhoto sendPhoto=new SendPhoto(callbackQuery.message().chat().id(),product.getImageUrl())
                    .caption(product.getName()
                            +
                            "\n"+"Материал: "+materialJpaRepository.findById(product.getMaterialId()).get().getMaterial()+
                            "\n"+"Марка: "+stampJpaRepository.findById(product.getStampId()).get().getStamp()+
                            "\n"+"Тип механизма: "+mechTypeJpaRepository.findById(product.getMechId()).get().getType()
                    )
                    .replyMarkup(new InlineKeyboardMarkup(inlineKeyboardButtons.toArray(new InlineKeyboardButton[0])));
            bot.execute(sendPhoto);
            inlineKeyboardButtons.clear();
            count++;
        }
        LOGGER.info(callbackQuery.data());
        DeleteMessage deleteMessage=new DeleteMessage(callbackQuery.message().chat().id(),callbackQuery.message().messageId());
        bot.execute(deleteMessage);
        Keyboard replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton("Магазин"),
                new KeyboardButton("Корзина"),
                new KeyboardButton("Заказы"))
                .oneTimeKeyboard(false)
                .resizeKeyboard(true)
                .selective(true);
        SendMessage sendMessage1=new SendMessage(callbackQuery.message().chat().id(),"Всего "+count+" штук")
                .replyMarkup(replyKeyboardMarkup);
        bot.execute(sendMessage1);
        SendMessage sendMessage=new SendMessage(callbackQuery.message().chat().id(),"Для того, чтобы вернуться, нажмите: ")
                .replyMarkup(new InlineKeyboardMarkup(new InlineKeyboardButton("Назад").callbackData(typeOfBack)));
        bot.execute(sendMessage);
        list.clear();
    }
}
