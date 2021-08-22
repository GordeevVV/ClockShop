package com.clockshop.service.handlers;

import com.clockshop.service.JDBSSimulation;
import com.clockshop.service.MessageTypes;
import com.clockshop.service.entity.Material;
import com.clockshop.service.entity.MechType;
import com.clockshop.service.entity.Product;
import com.clockshop.service.entity.Stamp;
import com.clockshop.service.repository.MaterialRepository;
import com.clockshop.service.repository.MechTypeRepository;
import com.clockshop.service.repository.ProductRepository;
import com.clockshop.service.repository.StampRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component(MessageTypes.JDBS)
public class JdbsHandler implements TelegramCallbackQueryHandler{
    TelegramBot bot;
    StampRepository stampRepository;
    MaterialRepository materialRepository;
    MechTypeRepository mechTypeRepository;
    ProductRepository productRepository;
    List<Product> list=new ArrayList<>();

    public JdbsHandler(TelegramBot bot, StampRepository stampRepository,
                       MaterialRepository materialRepository, MechTypeRepository mechTypeRepository,
                       ProductRepository productRepository) {
        this.bot = bot;
        this.stampRepository = stampRepository;
        this.materialRepository = materialRepository;
        this.mechTypeRepository = mechTypeRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void onCallBackQuery(CallbackQuery callbackQuery) {
        String typeOfBack=" ";
        if(JDBSSimulation.list1.contains(callbackQuery.data())){
            typeOfBack=MessageTypes.STAMP;
        for (Stamp stamp: stampRepository.getList()) {
            if(stamp.getStamp().equals(callbackQuery.data()))
                list.addAll(productRepository.findAllByStampId(stamp.getStampId()));
        }}else
        if(JDBSSimulation.list3.contains(callbackQuery.data())){
            typeOfBack=MessageTypes.TYPE_OF_MEASUREMENT;
        for(MechType mechType: mechTypeRepository.getList()){
            if(mechType.getType().equals(callbackQuery.data()))
                list.addAll(productRepository.findAllByMechId(mechType.getMechId()));
        }}else
        if(JDBSSimulation.list2.contains(callbackQuery.data())){
            typeOfBack=MessageTypes.CORP_MATERIAL;
        for(Material material: materialRepository.getList()){
            if(material.getMaterial().equals(callbackQuery.data()))
                list.addAll(productRepository.findAllByMaterialId(material.getMaterialId()));
        }}
        int count=0;
        List<InlineKeyboardButton>inlineKeyboardButtons=new ArrayList<>();
        for (Product product:list ) {
            inlineKeyboardButtons.add(new InlineKeyboardButton("Купить 1 шт "+product.getPrice()+" руб")
                    .callbackData(Integer.toString(product.getProductId())));
            SendPhoto sendPhoto=new SendPhoto(callbackQuery.message().chat().id(),product.getImageUrl()).caption(product.getName())
                    .replyMarkup(new InlineKeyboardMarkup(inlineKeyboardButtons.toArray(new InlineKeyboardButton[0])));
            bot.execute(sendPhoto);
            inlineKeyboardButtons.clear();
            count++;
        }

        DeleteMessage deleteMessage=new DeleteMessage(callbackQuery.message().chat().id(),callbackQuery.message().messageId());
        bot.execute(deleteMessage);
        Keyboard replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton("Магазин"),
                new KeyboardButton("Корзина"),
                new KeyboardButton("Заказы")).oneTimeKeyboard(false).resizeKeyboard(true).selective(true);
        SendMessage sendMessage1=new SendMessage(callbackQuery.message().chat().id(),"Всего "+count+" штук").replyMarkup(replyKeyboardMarkup);
        bot.execute(sendMessage1);
        SendMessage sendMessage=new SendMessage(callbackQuery.message().chat().id(),"Для того, чтобы вернуться, нажмите: ")
                .replyMarkup(new InlineKeyboardMarkup(new InlineKeyboardButton("Назад").callbackData(typeOfBack)));
        bot.execute(sendMessage);
        list.clear();
    }
}
