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

import java.time.LocalDateTime;
import java.util.List;

@Component(MessageTypes.ORDER)
public class OrderMessageHandler implements TelegramMessageHandler,TelegramCallbackQueryHandler{
    TelegramBot bot;
    OrderRepository orderRepository;
    OrderProductRepository orderProductRepository;
    ProductRepository productRepository;

    public OrderMessageHandler(TelegramBot bot, OrderRepository orderRepository
            ,OrderProductRepository orderProductRepository, ProductRepository productRepository) {
        this.bot = bot;
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void onCallBackQuery(CallbackQuery callbackQuery) {
        int i=0;
        for (Order order: orderRepository.getOrderList()){
            if(order.getStatus().equals("basket")) {
                order.setCreatedAt(LocalDateTime.now());
                order.setCustomerId(callbackQuery.message().from().id());
                order.setStatus("order");
                orderRepository.getOrderList().set(i,order);
            }
            i++;
        }
    }

    @Override
    public void onMessage(Message message) {
        for (Order order:orderRepository.getOrderList()) {
            if(order.getStatus().equals("order")) {
                for (OrderProduct orderproduct:orderProductRepository.getOrderProductList()) {
                    if(orderproduct.getOrderId()==order.getOrderId()) {
                        Product product=productRepository.getList().get(orderproduct.getProductId());
                        SendPhoto sendPhoto = new SendPhoto(message.chat().id(), product.getImageUrl())
                                .caption(product.getName() + "\n" + "Цена:" + product.getPrice() + " руб");
                        bot.execute(sendPhoto);
                    }
                }
                SendMessage sendMessage = new SendMessage(message.chat().id(), "Заказ #" + order.getOrderId() + "\n"
                        + "Дата: " + order.getCreatedAt() + "\n" + "" + order.getCalcPrice() + "\n" +
                        "Статус: Ожидает обработки" + "\n")
                        .replyMarkup(new InlineKeyboardMarkup(new InlineKeyboardButton("Оплатить").callbackData(MessageTypes.BUY_CONFIRMED+"_"
                        +order.getOrderId())));
                 bot.execute(sendMessage);
            }
        }
    }
}
