package com.clockshop.service.handlers;

import com.clockshop.service.MessageTypes;
import com.clockshop.service.entity.OrderProduct;
import com.clockshop.service.repository.OrderProductRepository;
import com.clockshop.service.repository.OrderRepository;
import com.clockshop.service.repository.ProductRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.request.DeleteMessage;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component(MessageTypes.DELETE)
public class BasketDeleteMessageHandler implements TelegramCallbackQueryHandler{
    TelegramBot bot;
    OrderRepository orderRepository;
    OrderProductRepository orderProductRepository;
    ProductRepository productRepository;

    public BasketDeleteMessageHandler(TelegramBot bot, OrderRepository orderRepository
            , OrderProductRepository orderProductRepository, ProductRepository productRepository) {
        this.bot = bot;
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void onCallBackQuery(CallbackQuery callbackQuery) {
        DeleteMessage deleteMessage=new DeleteMessage(callbackQuery.message().chat().id(),callbackQuery.message().messageId());
        bot.execute(deleteMessage);
        String prefix="Delete_";
        int deleteProductId=Integer.parseInt(callbackQuery.data()
                .substring(callbackQuery.data().indexOf(prefix)+prefix.length()));
        int deleteOrderId=0;
        for (OrderProduct orderProduct:orderProductRepository.getOrderProductList()){
            if(orderProduct.getProductId()==deleteProductId)
                deleteOrderId=orderProduct.getOrderId();
        }
       orderProductRepository.deleteProductFromOrderProductList(deleteProductId);
       orderRepository.deleteFromList(deleteOrderId);
    }
}
