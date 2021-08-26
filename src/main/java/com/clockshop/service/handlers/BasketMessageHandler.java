package com.clockshop.service.handlers;

import com.clockshop.service.MessageTypes;
import com.clockshop.service.entity.Customer;
import com.clockshop.service.entity.Order;
import com.clockshop.service.entity.OrderProduct;
import com.clockshop.service.entity.Product;
import com.clockshop.service.repository.*;
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
    OrderJpaRepository orderJpaRepository;
    OrderProductJpaRepository orderProductJpaRepository;
    ProductJpaRepository productJpaRepository;
    CustomerJpaRepository customerJpaRepository;

    public BasketMessageHandler(TelegramBot bot, OrderJpaRepository orderJpaRepository, OrderProductJpaRepository orderProductJpaRepository
            , ProductJpaRepository productJpaRepository, CustomerJpaRepository customerJpaRepository) {
        this.bot = bot;
        this.orderJpaRepository = orderJpaRepository;
        this.orderProductJpaRepository = orderProductJpaRepository;
        this.productJpaRepository = productJpaRepository;
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    public void onCallBackQuery(CallbackQuery callbackQuery) {
        Customer customer = customerJpaRepository.findById(callbackQuery.from().id()).orElseGet(() -> {
            Customer c = new Customer(callbackQuery.from().id(),
                    callbackQuery.from().firstName(), callbackQuery.from().lastName()
                    , callbackQuery.from().username());
            customerJpaRepository.save(c);
            return c;
        });
        Product product = productJpaRepository.findById(Integer.parseInt(callbackQuery.data())).get();
        Order order = orderJpaRepository.findByStatusAndCustomerId("basket", customer.getCustomerId()).orElseGet(() -> {
            Order o = new Order();
            o.setCalcPrice(0);
            o.setStatus("basket");
            o.setCustomerId(customer.getCustomerId());
            return o;
        });
        order.setCalcPrice(order.getCalcPrice() + product.getPrice());
        order=orderJpaRepository.save(order);
        OrderProduct orderProduct = new OrderProduct(order.getOrderId(), product.getProductId());
        orderProductJpaRepository.save(orderProduct);
    }

    @Override
    public void onMessage(Message message) {
        Order order;
        if(orderJpaRepository.findByStatusAndCustomerId("basket",message.from().id()).isPresent()) {
            order = orderJpaRepository.findByStatusAndCustomerId("basket", message.from().id()).get();
            List<OrderProduct> orderProducts = orderProductJpaRepository.findAllByOrderId(order.getOrderId());
            for (OrderProduct orderproduct : orderProducts) {
                Product product = productJpaRepository.findById(orderproduct.getProductId()).get();
                List<InlineKeyboardButton> inlineKeyboardButtons =
                        new ArrayList<>(new ArrayList<>(Arrays.asList(
                                new InlineKeyboardButton("Исключить из заказа")
                                        .callbackData("Delete_" + orderproduct.getOrderproductId())
                        )));
                SendPhoto sendPhoto = new SendPhoto(message.chat().id(), product.getImageUrl())
                        .caption(product.getName() + "\n" + "Цена:" + product.getPrice() + " руб")
                        .replyMarkup(new InlineKeyboardMarkup(inlineKeyboardButtons.toArray(new InlineKeyboardButton[0])));
                bot.execute(sendPhoto);
                inlineKeyboardButtons.clear();
            }
            SendMessage sendMessage = new SendMessage(message.chat().id(), "Заказ:")
                    .replyMarkup(new InlineKeyboardMarkup
                            (new InlineKeyboardButton("Оформить").callbackData(MessageTypes.ORDER),
                                    new InlineKeyboardButton("Домой").callbackData(MessageTypes.HOME)));
            bot.execute(sendMessage);
        }
    }
}
