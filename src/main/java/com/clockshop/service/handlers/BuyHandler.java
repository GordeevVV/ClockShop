package com.clockshop.service.handlers;

import com.clockshop.service.MessageTypes;
import com.clockshop.service.entity.Order;
import com.clockshop.service.repository.OrderJpaRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component(MessageTypes.BUY_CONFIRMED)
public class BuyHandler implements TelegramCallbackQueryHandler{
    static Logger LOGGER= LoggerFactory.getLogger(JdbsHandler.class);
    TelegramBot bot;
    OrderJpaRepository orderJpaRepository;

    public BuyHandler(TelegramBot bot, OrderJpaRepository orderJpaRepository) {
        this.bot = bot;
        this.orderJpaRepository=orderJpaRepository;
    }

    @Override
    public void onCallBackQuery(CallbackQuery callbackQuery) {
        String prefix=MessageTypes.BUY_CONFIRMED+"_";
        int boughtOrderId=Integer.parseInt(callbackQuery.data()
                .substring(callbackQuery.data().indexOf(prefix)+prefix.length()));
        Order order=orderJpaRepository.findById(boughtOrderId).get();
        order.setStatus("bought");
        orderJpaRepository.save(order);
        SendMessage sendMessage=new SendMessage(callbackQuery.message().chat().id(),"Поздравляем с покупкой!");
        bot.execute(sendMessage);
        LOGGER.info("Buy confirmed");
    }
}
