package com.clockshop.service.tasks;

import com.clockshop.service.constants.MessageTypes;
import com.clockshop.service.entity.Manager;
import com.clockshop.service.entity.Order;
import com.clockshop.service.entity.OrderProduct;
import com.clockshop.service.entity.Product;
import com.clockshop.service.repository.ManagerJpaRepository;
import com.clockshop.service.repository.OrderJpaRepository;
import com.clockshop.service.repository.OrderProductJpaRepository;
import com.clockshop.service.repository.ProductJpaRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderNotifier {
    private static final Logger log = LoggerFactory.getLogger(OrderNotifier.class);
    private TelegramBot bot;
    private OrderJpaRepository orderJpaRepository;
    private OrderProductJpaRepository orderProductJpaRepository;
    private ProductJpaRepository productJpaRepository;
    private ManagerJpaRepository managerJpaRepository;


    public OrderNotifier(@Qualifier("ManagerBot") TelegramBot bot, OrderJpaRepository orderJpaRepository, OrderProductJpaRepository orderProductJpaRepository
            , ProductJpaRepository productJpaRepository, ManagerJpaRepository managerJpaRepository) {
        this.bot = bot;
        this.orderJpaRepository = orderJpaRepository;
        this.orderProductJpaRepository = orderProductJpaRepository;
        this.productJpaRepository = productJpaRepository;
        this.managerJpaRepository = managerJpaRepository;
    }

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        String str="";
        for (Manager manager : managerJpaRepository.findAllByChatIdNotNull()) {
                for (Order order : orderJpaRepository.findAllByStatusAndCustomerId("bought", manager.getChatId())) {
                    for (OrderProduct orderproduct : orderProductJpaRepository.findAllByOrderId(order.getOrderId())) {
                        Product product = productJpaRepository.findById(orderproduct.getProductId()).get();
                        str+=product.getName() + "\n" + "Цена:" + product.getPrice() + " руб"+"\n";
                    }
                    SendMessage sendMessage = new SendMessage(manager.getChatId(),str+ "Заказ #" + order.getOrderId() + "\n"
                            + "Дата: " + order.getCreatedAt() + "\n" + "Стоимость заказа: " + order.getCalcPrice());
                    bot.execute(sendMessage);
                    order.setStatus("finished");
                    orderJpaRepository.save(order);
                }
        }
    }
}
