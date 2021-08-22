package com.clockshop.service.listeners;

import com.clockshop.service.JDBSSimulation;
import com.clockshop.service.MessageTypes;
import com.clockshop.service.handlers.TelegramCallbackQueryHandler;
import com.clockshop.service.handlers.TelegramMessageHandler;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Component
public class ClockUpdateListener implements UpdatesListener {
    private Map<String, TelegramMessageHandler > telegramMessageHandlerMap;
    private Map<String,TelegramCallbackQueryHandler> telegramCallbackQueryHandlerMap;
    private TelegramBot bot;

    public ClockUpdateListener(Map< String, TelegramMessageHandler > map,TelegramBot bot,Map<String,TelegramCallbackQueryHandler> map1) {
        this.telegramMessageHandlerMap = map;
        this.bot=bot;
        this.telegramCallbackQueryHandlerMap =map1;
    }
    @PostConstruct
    public void postConstruct(){
        bot.setUpdatesListener(this);
    }

    @Override
    public int process(List< Update > list) {

        for (Update update:list) {
            if(update.message()!=null) {
                if (update.message().text().equals("/start") || update.message().text().equals("Домой"))
                    telegramMessageHandlerMap.get(MessageTypes.HOME).onMessage(update.message());
                if (update.message().text().equals("Магазин"))
                    telegramMessageHandlerMap.get(MessageTypes.SHOP).onMessage(update.message());
                if (update.message().text().equals("Корзина"))
                    telegramMessageHandlerMap.get(MessageTypes.BASKET).onMessage(update.message());
                if (update.message().text().equals("Заказы"))
                    telegramMessageHandlerMap.get(MessageTypes.ORDER).onMessage(update.message());
                System.out.println(update.message().toString());
            }if(update.callbackQuery()!=null){
                if(update.callbackQuery().data().equalsIgnoreCase(MessageTypes.STAMP))
                    telegramCallbackQueryHandlerMap.get(MessageTypes.STAMP).onCallBackQuery(update.callbackQuery());
                else if(update.callbackQuery().data().equalsIgnoreCase(MessageTypes.CORP_MATERIAL))
                    telegramCallbackQueryHandlerMap.get(MessageTypes.CORP_MATERIAL).onCallBackQuery(update.callbackQuery());
                else if(update.callbackQuery().data().equalsIgnoreCase(MessageTypes.TYPE_OF_MEASUREMENT))
                    telegramCallbackQueryHandlerMap.get(MessageTypes.TYPE_OF_MEASUREMENT).onCallBackQuery(update.callbackQuery());

                else if(update.callbackQuery().data().equalsIgnoreCase(MessageTypes.SHOP))
                    telegramMessageHandlerMap.get(MessageTypes.SHOP).onMessage(update.callbackQuery().message());
                else if(update.callbackQuery().data().equalsIgnoreCase(MessageTypes.HOME))
                    telegramMessageHandlerMap.get(MessageTypes.HOME).onMessage(update.callbackQuery().message());
                else if(JDBSSimulation.list1.contains(update.callbackQuery().data())
                        ||JDBSSimulation.list2.contains(update.callbackQuery().data())
                        ||JDBSSimulation.list3.contains(update.callbackQuery().data()))
                    telegramCallbackQueryHandlerMap.get(MessageTypes.JDBS).onCallBackQuery(update.callbackQuery());
                else if(update.callbackQuery().data().matches("^Delete_[0-9]+$"))
                    telegramCallbackQueryHandlerMap.get(MessageTypes.DELETE).onCallBackQuery(update.callbackQuery());
                else if(update.callbackQuery().data().matches("^[0-9]+$"))
                    telegramCallbackQueryHandlerMap.get(MessageTypes.BASKET).onCallBackQuery(update.callbackQuery());
                else if(update.callbackQuery().data().matches("^"+MessageTypes.BUY_CONFIRMED+"_[0-9]+$"))
                telegramCallbackQueryHandlerMap.get(MessageTypes.BUY_CONFIRMED).onCallBackQuery(update.callbackQuery());
                else if(update.callbackQuery().data().equals(MessageTypes.ORDER))
                    telegramCallbackQueryHandlerMap.get(MessageTypes.ORDER).onCallBackQuery(update.callbackQuery());
            }
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
   public String getMessageType(Update update){
        try {
            if (update.message() != null) {
                if (update.message().text().equals("/start") || update.message().text().equals("Домой"))
                    return MessageTypes.HOME;
                if (update.message().text().equals("Магазин"))
                    return MessageTypes.SHOP;
                if (update.message().text().equals("Корзина"))
                    return MessageTypes.BASKET;
                if (update.message().text().equals("Заказы"))
                    return MessageTypes.ORDER;
            }
            if (update.callbackQuery() != null) {
                if (update.callbackQuery().data().equalsIgnoreCase(MessageTypes.STAMP))
                    return MessageTypes.STAMP;
                else if (update.callbackQuery().data().equalsIgnoreCase(MessageTypes.CORP_MATERIAL))
                    return MessageTypes.CORP_MATERIAL;
                else if (update.callbackQuery().data().equalsIgnoreCase(MessageTypes.TYPE_OF_MEASUREMENT))
                    return MessageTypes.TYPE_OF_MEASUREMENT;
                else if (update.callbackQuery().data().equalsIgnoreCase(MessageTypes.SHOP))
                    return MessageTypes.SHOP;
                else if (update.callbackQuery().data().equalsIgnoreCase(MessageTypes.HOME))
                    return MessageTypes.HOME;
                else if (JDBSSimulation.list1.contains(update.callbackQuery().data())
                        || JDBSSimulation.list2.contains(update.callbackQuery().data())
                        || JDBSSimulation.list3.contains(update.callbackQuery().data()))
                    return MessageTypes.JDBS;
                else if (update.callbackQuery().data().matches("^Delete_[0-9]+$"))
                    return MessageTypes.DELETE;
                else if (update.callbackQuery().data().matches("^[0-9]+$"))
                    return MessageTypes.BASKET;
            }else return MessageTypes.DEFAULT;
        }catch (NullPointerException nullPointerException) {
           // return MessageTypes.DEFAULT;
        }
                return null;
    }
}

/*if(update.message()!=null) {
        //  telegramMessageHandlerMap.get(getMessageType(update)).onMessage(update.message());
        if (update.message().text().equals("/start") || update.message().text().equals("Домой"))
        telegramMessageHandlerMap.get(MessageTypes.HOME).onMessage(update.message());
        if (update.message().text().equals("Магазин"))
        telegramMessageHandlerMap.get(MessageTypes.SHOP).onMessage(update.message());
        if (update.message().text().equals("Корзина"))
        telegramMessageHandlerMap.get(MessageTypes.BASKET).onMessage(update.message());
        if (update.message().text().equals("Заказы"))
        telegramMessageHandlerMap.get(MessageTypes.ORDER).onMessage(update.message());
        System.out.println(update.message().toString());
        }if(update.callbackQuery()!=null){
//                try {
//                    telegramCallbackQueryHandlerMap.get(getMessageType(update)).onCallBackQuery(update.callbackQuery());
//                }catch (Exception e){
//                    telegramMessageHandlerMap.get(getMessageType(update)).onMessage(update.callbackQuery().message());
//                }}
        if(update.callbackQuery().data().equalsIgnoreCase(MessageTypes.STAMP))
        telegramCallbackQueryHandlerMap.get(MessageTypes.STAMP).onCallBackQuery(update.callbackQuery());
        else if(update.callbackQuery().data().equalsIgnoreCase(MessageTypes.CORP_MATERIAL))
        telegramCallbackQueryHandlerMap.get(MessageTypes.CORP_MATERIAL).onCallBackQuery(update.callbackQuery());
        else if(update.callbackQuery().data().equalsIgnoreCase(MessageTypes.TYPE_OF_MEASUREMENT))
        telegramCallbackQueryHandlerMap.get(MessageTypes.TYPE_OF_MEASUREMENT).onCallBackQuery(update.callbackQuery());

        else if(update.callbackQuery().data().equalsIgnoreCase(MessageTypes.SHOP))
        telegramMessageHandlerMap.get(MessageTypes.SHOP).onMessage(update.callbackQuery().message());
        else if(update.callbackQuery().data().equalsIgnoreCase(MessageTypes.HOME))
        telegramMessageHandlerMap.get(MessageTypes.HOME).onMessage(update.callbackQuery().message());
        else if(JDBSSimulation.list1.contains(update.callbackQuery().data())
        ||JDBSSimulation.list2.contains(update.callbackQuery().data())
        ||JDBSSimulation.list3.contains(update.callbackQuery().data()))
        telegramCallbackQueryHandlerMap.get(MessageTypes.JDBS).onCallBackQuery(update.callbackQuery());
        else if(update.callbackQuery().data().matches("^Delete_[0-9]+$"))
        telegramCallbackQueryHandlerMap.get(MessageTypes.DELETE).onCallBackQuery(update.callbackQuery());
        else if(update.callbackQuery().data().matches("^[0-9]+$"))
        telegramCallbackQueryHandlerMap.get(MessageTypes.BASKET).onCallBackQuery(update.callbackQuery());}*/