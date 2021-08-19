package com.clockshop.service.listeners;

import com.clockshop.service.JDBSSimulation;
import com.clockshop.service.MessageTypes;
import com.clockshop.service.handlers.TelegramCallbackQueryHandler;
import com.clockshop.service.handlers.TelegramMessageHandler;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class ClockUpdateListener implements UpdatesListener {
    private Map<String, TelegramMessageHandler > map;
    private Map<String,TelegramCallbackQueryHandler> map1;
    private TelegramBot bot;

    public ClockUpdateListener(Map< String, TelegramMessageHandler > map,TelegramBot bot,Map<String,TelegramCallbackQueryHandler> map1) {
        this.map = map;
        this.bot=bot;
        this.map1=map1;
    }
    @PostConstruct
    public void postConstruct(){
        bot.setUpdatesListener(this);
       /* Keyboard keyboard = new ReplyKeyboardMarkup(
                        new KeyboardButton("Старт")
        );
        SendMessage sendMessage=new SendMessage(,"Запустить").replyMarkup(keyboard);*/
    }

    @Override
    public int process(List< Update > list) {

        for (Update update:list) {
           if(update.message()!=null) {
               if (update.message().text().equals("/start") || update.message().text().equals("Домой"))
                   map.get(MessageTypes.HOME).onMessage(update.message());
               if (update.message().text().equals("Магазин"))
                   map.get(MessageTypes.SHOP).onMessage(update.message());
//               if (update.message().text().equals("Заказы"))
//                   map.get(MessageTypes.ORDER).onMessage(update.message());
               if (update.message().text().equals("Корзина"))
                   map.get(MessageTypes.BASKET).onMessage(update.message());
           }
            if(update.callbackQuery()!=null){
                if(update.callbackQuery().data().equalsIgnoreCase("Марка"))
                    map.get(MessageTypes.STAMP).onMessage(update.callbackQuery().message());
                else if(update.callbackQuery().data().equalsIgnoreCase("Материал корпуса"))
                    map.get(MessageTypes.CORP_MATERIAL).onMessage(update.callbackQuery().message());
                else if(update.callbackQuery().data().equalsIgnoreCase("Вид измерения"))
                    map.get(MessageTypes.TYPE_OF_MEASUREMENT).onMessage(update.callbackQuery().message());

                else if(update.callbackQuery().data().equalsIgnoreCase("Назад1"))
                    map.get(MessageTypes.SHOP).onMessage(update.callbackQuery().message());
                else if(update.callbackQuery().data().equalsIgnoreCase("Назад2")) {
                  if(JDBSSimulation.list1.contains(update.callbackQuery().message().text()))
                    map.get(MessageTypes.STAMP).onMessage(update.callbackQuery().message());
                    if(JDBSSimulation.list2.contains(update.callbackQuery().message().text()))
                        map.get(MessageTypes.CORP_MATERIAL).onMessage(update.callbackQuery().message());
                    if(JDBSSimulation.list3.contains(update.callbackQuery().message().text()))
                        map.get(MessageTypes.TYPE_OF_MEASUREMENT).onMessage(update.callbackQuery().message());
                }
                else if(JDBSSimulation.list1.contains(update.callbackQuery().data())
                ||JDBSSimulation.list2.contains(update.callbackQuery().data())
                ||JDBSSimulation.list3.contains(update.callbackQuery().data()))
                    map1.get(MessageTypes.JDBS).onCallBackQuery(update.callbackQuery());
                else if(update.callbackQuery().data().equalsIgnoreCase("Заказы"))
                    map1.get(MessageTypes.ORDER).onCallBackQuery(update.callbackQuery());
            }
           /* if(update.message().text().equals("Магазин"))
                map.get(MessageTypes.SHOP).onMessage(update.message());*/
           // System.out.println(update.message().toString());
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }



}
