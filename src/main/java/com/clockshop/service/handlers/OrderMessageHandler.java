package com.clockshop.service.handlers;

import com.clockshop.service.MessageTypes;
import com.clockshop.service.entity.Order;
import com.clockshop.service.entity.OrderProduct;
import com.clockshop.service.entity.Product;
import com.clockshop.service.repository.OrderJpaRepository;
import com.clockshop.service.repository.OrderProductJpaRepository;
import com.clockshop.service.repository.ProductJpaRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component(MessageTypes.ORDER)
public class OrderMessageHandler implements TelegramMessageHandler,TelegramCallbackQueryHandler{
    TelegramBot bot;
    OrderJpaRepository orderJpaRepository;
    OrderProductJpaRepository orderProductJpaRepository;
    ProductJpaRepository productJpaRepository;


    public OrderMessageHandler(TelegramBot bot, OrderJpaRepository orderJpaRepository
            , OrderProductJpaRepository orderProductJpaRepository, ProductJpaRepository productJpaRepository) {
        this.bot = bot;
        this.orderJpaRepository = orderJpaRepository;
        this.orderProductJpaRepository = orderProductJpaRepository;
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public void onCallBackQuery(CallbackQuery callbackQuery) {
        if(orderJpaRepository.findByStatusAndCustomerId("basket",callbackQuery.from().id()).isPresent()) {
            Order order = orderJpaRepository.findByStatusAndCustomerId("basket", callbackQuery.from().id()).get();
            order.setCreatedAt(LocalDateTime.now());
            order.setStatus("order");
            orderJpaRepository.save(order);
        }
    }

    @Override
    public void onMessage(Message message) {
        for (Order order:orderJpaRepository.findAllByStatusAndCustomerId("order",message.from().id())) {
                for (OrderProduct orderproduct:orderProductJpaRepository.findAllByOrderId(order.getOrderId())) {
                        Product product=productJpaRepository.findAll().get(orderproduct.getProductId());
                        SendPhoto sendPhoto = new SendPhoto(message.chat().id(), product.getImageUrl())
                                .caption(product.getName() + "\n" + "Цена:" + product.getPrice() + " руб");
                        bot.execute(sendPhoto);
                }
                SendMessage sendMessage = new SendMessage(message.chat().id(), "Заказ #" + order.getOrderId() + "\n"
                        + "Дата: " + order.getCreatedAt() + "\n" + "Стоимость заказа: " + order.getCalcPrice() + "\n" +
                        "Статус: Ожидает обработки" + "\n")
                        .replyMarkup(new InlineKeyboardMarkup(new InlineKeyboardButton("Оплатить")
                                .callbackData(MessageTypes.BUY_CONFIRMED+"_"+order.getOrderId())));
                 bot.execute(sendMessage);
        }
    }
}
