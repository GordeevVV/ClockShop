package com.clockshop.service.handlers;

import com.clockshop.service.constants.MessageTypes;
import com.clockshop.service.entity.Order;
import com.clockshop.service.entity.OrderProduct;
import com.clockshop.service.repository.OrderJpaRepository;
import com.clockshop.service.repository.OrderProductJpaRepository;
import com.clockshop.service.repository.ProductJpaRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.request.DeleteMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component(MessageTypes.DELETE)
public class BasketDeleteMessageHandler implements TelegramCallbackQueryHandler{
    TelegramBot bot;
    OrderJpaRepository orderJpaRepository;
    OrderProductJpaRepository orderProductJpaRepository;
    ProductJpaRepository productJpaRepository;

    public BasketDeleteMessageHandler(@Qualifier("ShopBot") TelegramBot bot, OrderJpaRepository orderJpaRepository
            , OrderProductJpaRepository orderProductJpaRepository, ProductJpaRepository productJpaRepository) {
        this.bot = bot;
        this.orderJpaRepository = orderJpaRepository;
        this.orderProductJpaRepository = orderProductJpaRepository;
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public void onCallBackQuery(CallbackQuery callbackQuery) {
        String prefix="Delete_";
        int deleteOrderProductId=Integer.parseInt(callbackQuery.data()
                .substring(callbackQuery.data().indexOf(prefix)+prefix.length()));
        OrderProduct orderProduct=orderProductJpaRepository.findById(deleteOrderProductId).get();
        Order order=orderJpaRepository.findById(orderProduct.getOrderId()).get();
        order.setCalcPrice(order.getCalcPrice()-productJpaRepository.findById(
                orderProduct.getProductId()).get().getPrice());
        orderJpaRepository.save(order);
        orderProductJpaRepository.deleteById(deleteOrderProductId);
        DeleteMessage deleteMessage=new DeleteMessage(callbackQuery.message().chat().id(),callbackQuery.message().messageId());
        bot.execute(deleteMessage);
    }
}
