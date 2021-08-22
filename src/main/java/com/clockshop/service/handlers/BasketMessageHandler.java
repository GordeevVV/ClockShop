package com.clockshop.service.handlers;

import com.clockshop.service.MessageTypes;
import com.clockshop.service.entity.Order;
import com.clockshop.service.entity.OrderProduct;
import com.clockshop.service.entity.Product;
import com.clockshop.service.repository.OrderProductRepository;
import com.clockshop.service.repository.OrderRepository;
import com.clockshop.service.repository.ProductRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component(MessageTypes.BASKET)
public class BasketMessageHandler implements TelegramCallbackQueryHandler,TelegramMessageHandler{
    TelegramBot bot;
    OrderRepository orderRepository;
    OrderProductRepository orderProductRepository;
    ProductRepository productRepository;


    public BasketMessageHandler(TelegramBot bot, OrderRepository orderRepository
            , OrderProductRepository orderProductRepository, ProductRepository productRepository) {
        this.bot = bot;
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void onCallBackQuery(CallbackQuery callbackQuery) {
       Product product=productRepository.getList().get(Integer.parseInt(callbackQuery.data()));
       Order order1=new Order(-1,200,"..");
       for (Order order:orderRepository.getOrderList()){
           if(order.getStatus().equals("basket")) {
               order1 = order;
               order1.setCalcPrice(order.getCalcPrice()+product.getPrice());
               OrderProduct orderProduct=new OrderProduct(order.getOrderId(), product.getProductId());
               orderProductRepository.setOrderProduct(orderProduct);
           }
       }
       if(order1.getOrderId()==-1) {
           order1.setOrderId(orderRepository.getOrderList().hashCode());
           order1.setCalcPrice(product.getPrice());
           order1.setStatus("basket");
           OrderProduct orderProduct=new OrderProduct(order1.getOrderId(), product.getProductId());
           orderProductRepository.setOrderProduct(orderProduct);
       }
       orderRepository.insertInList(order1);
    }

    @Override
    public void onMessage(Message message) {
        for(Order order:orderRepository.getOrderList()){
            if(order.getStatus().equals("basket")){
                for (OrderProduct orderproduct:orderProductRepository.getOrderProductList()) {
                    if(orderproduct.getOrderId()==order.getOrderId()){
                        Product product=productRepository.getList().get(orderproduct.getProductId());
                        List< InlineKeyboardButton > inlineKeyboardButtons = new ArrayList<>(new ArrayList< InlineKeyboardButton >(Arrays.asList(
                                new InlineKeyboardButton("Исключить из заказа")
                                        .callbackData("Delete_" + product.getProductId())
                        )));
                        SendPhoto sendPhoto=new SendPhoto(message.chat().id(),product.getImageUrl())
                                .caption(product.getName()+"\n"+"Цена:"+product.getPrice()+" руб")
                                .replyMarkup(new InlineKeyboardMarkup(inlineKeyboardButtons.toArray(new InlineKeyboardButton[0])));
                        bot.execute(sendPhoto);
                        inlineKeyboardButtons.clear();
                    }
                }
                SendMessage sendMessage=new SendMessage(message.chat().id(),"Заказ:")
                        .replyMarkup(new InlineKeyboardMarkup
                                (new InlineKeyboardButton("Оформить").callbackData(MessageTypes.ORDER),
                                        new InlineKeyboardButton("Домой").callbackData(MessageTypes.HOME)));
            bot.execute(sendMessage);
            }
        }
    }
}
