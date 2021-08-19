package com.clockshop.service.handlers;

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
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.SendMessage;
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
        for (Stamp stamp: stampRepository.getList()) {
            if(stamp.getStamp().equals(callbackQuery.data()))
                list.addAll(productRepository.findAllByStampId(stamp.getStampId()));
        }

        for(MechType mechType: mechTypeRepository.getList()){
            if(mechType.getType().equals(callbackQuery.data()))
                list.addAll(productRepository.findAllByMechId(mechType.getMechId()));
        }
        for(Material material: materialRepository.getList()){
            if(material.getMaterial().equals(callbackQuery.data()))
                list.addAll(productRepository.findAllByMaterialId(material.getMaterialId()));
        }
        List<InlineKeyboardButton[]>inlineKeyboardRows=new ArrayList<>();
        List<InlineKeyboardButton>inlineKeyboardButtons=new ArrayList<>();
        for (Product product:list ) {
            inlineKeyboardButtons.add(new InlineKeyboardButton("Изображение: ").switchInlineQuery(product.getImageUrl()));
            inlineKeyboardRows.add(inlineKeyboardButtons.toArray(new InlineKeyboardButton[0]));
            inlineKeyboardButtons.clear();
            inlineKeyboardButtons.addAll(new ArrayList<InlineKeyboardButton>(Arrays.asList(
                    new InlineKeyboardButton(product.getName()).callbackData(product.getName()),
            new InlineKeyboardButton("Купить 1 шт "+product.getPrice()+" руб").callbackData("Купил")
            ))
        );
            inlineKeyboardRows.add(inlineKeyboardButtons.toArray(new InlineKeyboardButton[0]));
            inlineKeyboardButtons.clear();
        }
        inlineKeyboardButtons.add(new InlineKeyboardButton("Назад").callbackData("Назад2"));
        inlineKeyboardRows.add(inlineKeyboardButtons.toArray(new InlineKeyboardButton[0]));
        inlineKeyboardButtons.clear();
        InlineKeyboardMarkup keyboard=new InlineKeyboardMarkup(inlineKeyboardRows.toArray(new InlineKeyboardButton[0][0]));
        DeleteMessage deleteMessage=new DeleteMessage(callbackQuery.message().chat().id(),callbackQuery.message().messageId());
        bot.execute(deleteMessage);
        inlineKeyboardRows.clear();
        Keyboard replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton("Магазин"),
                new KeyboardButton("Корзина"),
                new KeyboardButton("Заказы")).oneTimeKeyboard(false).resizeKeyboard(true).selective(true);
        SendMessage sendMessage1=new SendMessage(callbackQuery.message().chat().id()," ").replyMarkup(replyKeyboardMarkup);
        bot.execute(sendMessage1);
        SendMessage sendMessage=new SendMessage(callbackQuery.message().chat().id(), callbackQuery.data()).
                replyMarkup(keyboard);
        bot.execute(sendMessage);
    }
}
