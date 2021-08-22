package com.clockshop.service.handlers;

import com.clockshop.service.MessageTypes;
import com.clockshop.service.entity.OrderProduct;
import com.clockshop.service.repository.OrderProductRepository;
import com.clockshop.service.repository.OrderRepository;
import com.clockshop.service.repository.ProductRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component(MessageTypes.BUY_CONFIRMED)
public class BuyHandler implements TelegramCallbackQueryHandler{
    TelegramBot bot;
    OrderRepository orderRepository;
    OrderProductRepository orderProductRepository;
    ProductRepository productRepository;

    public BuyHandler(TelegramBot bot, OrderRepository orderRepository
            , OrderProductRepository orderProductRepository, ProductRepository productRepository) {
        this.bot = bot;
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void onCallBackQuery(CallbackQuery callbackQuery) {
        String prefix=MessageTypes.BUY_CONFIRMED+"_";
        int deleteOrderId=Integer.parseInt(callbackQuery.data()
                .substring(callbackQuery.data().indexOf(prefix)+prefix.length()));
        orderRepository.deleteFromList(deleteOrderId);
        for (OrderProduct orderProduct:orderProductRepository.getOrderProductList()){
            if(orderProduct.getOrderId()==deleteOrderId)
                orderProductRepository.deleteOrderFromOrderProductList(deleteOrderId);
        }
        SendMessage sendMessage=new SendMessage(callbackQuery.message().chat().id(),"Поздравляем с покупкой!");
    }
}
