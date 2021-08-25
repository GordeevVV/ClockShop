package com.clockshop.service.handlers;

import com.clockshop.service.MessageTypes;
import com.clockshop.service.entity.Order;
import com.clockshop.service.repository.OrderJpaRepository;
import com.clockshop.service.repository.OrderProductJpaRepository;
import com.clockshop.service.repository.ProductJpaRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.request.DeleteMessage;
import org.springframework.stereotype.Component;


@Component(MessageTypes.DELETE)
public class BasketDeleteMessageHandler implements TelegramCallbackQueryHandler{
    TelegramBot bot;
    OrderJpaRepository orderJpaRepository;
    OrderProductJpaRepository orderProductJpaRepository;
    ProductJpaRepository productJpaRepository;

    public BasketDeleteMessageHandler(TelegramBot bot, OrderJpaRepository orderJpaRepository
            , OrderProductJpaRepository orderProductJpaRepository, ProductJpaRepository productJpaRepository) {
        this.bot = bot;
        this.orderJpaRepository = orderJpaRepository;
        this.orderProductJpaRepository = orderProductJpaRepository;
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public void onCallBackQuery(CallbackQuery callbackQuery) {
        DeleteMessage deleteMessage=new DeleteMessage(callbackQuery.message().chat().id(),callbackQuery.message().messageId());
        bot.execute(deleteMessage);
        String prefix="Delete_";
        int deleteOrderProductId=Integer.parseInt(callbackQuery.data()
                .substring(callbackQuery.data().indexOf(prefix)+prefix.length()));

        Order order=orderJpaRepository.findById(orderProductJpaRepository.findById(deleteOrderProductId).get().getOrderId()).get();
        order.setCalcPrice(order.getCalcPrice()-productJpaRepository.findById(
                orderProductJpaRepository.findById(deleteOrderProductId).get().getProductId()).get().getPrice());
        orderJpaRepository.save(order);
       orderProductJpaRepository.deleteById(deleteOrderProductId);
    }
}
