package com.clockshop.service.handlers;

import com.clockshop.service.constants.MessageTypes;
import com.clockshop.service.entity.Log;
import com.clockshop.service.entity.Order;
import com.clockshop.service.entity.OrderProduct;
import com.clockshop.service.entity.Product;
import com.clockshop.service.repository.LogJpaRepository;
import com.clockshop.service.repository.MaterialJpaRepository;
import com.clockshop.service.repository.MechTypeJpaRepository;
import com.clockshop.service.repository.OrderJpaRepository;
import com.clockshop.service.repository.OrderProductJpaRepository;
import com.clockshop.service.repository.ProductJpaRepository;
import com.clockshop.service.repository.StampJpaRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component(MessageTypes.BUY_CONFIRMED)
public class BuyHandler implements TelegramCallbackQueryHandler {
    static Logger LOGGER = LoggerFactory.getLogger(JdbsHandler.class);
    TelegramBot bot;
    private final OrderJpaRepository orderJpaRepository;

    private final OrderProductJpaRepository orderProductJpaRepository;

    private final ProductJpaRepository productJpaRepository;

    private final StampJpaRepository stampJpaRepository;

    private final MechTypeJpaRepository mechTypeJpaRepository;

    private final MaterialJpaRepository materialJpaRepository;

    private final LogJpaRepository logJpaRepository;

    public BuyHandler(@Qualifier("ShopBot") TelegramBot bot, OrderJpaRepository orderJpaRepository, OrderProductJpaRepository orderProductJpaRepository,
                      ProductJpaRepository productJpaRepository, StampJpaRepository stampJpaRepository, MechTypeJpaRepository mechTypeJpaRepository,
                      MaterialJpaRepository materialJpaRepository, LogJpaRepository logJpaRepository) {
        this.bot = bot;
        this.orderJpaRepository = orderJpaRepository;
        this.orderProductJpaRepository = orderProductJpaRepository;
        this.productJpaRepository = productJpaRepository;
        this.stampJpaRepository = stampJpaRepository;
        this.mechTypeJpaRepository = mechTypeJpaRepository;
        this.materialJpaRepository = materialJpaRepository;
        this.logJpaRepository = logJpaRepository;
    }

    @Override
    public void onCallBackQuery(CallbackQuery callbackQuery) {
        String prefix = MessageTypes.BUY_CONFIRMED + "_";
        int boughtOrderId = Integer.parseInt(callbackQuery.data()
                .substring(callbackQuery.data().indexOf(prefix) + prefix.length()));
        Order order = orderJpaRepository.findById(boughtOrderId).get();
        order.setStatus("bought");
        orderJpaRepository.save(order);
        SendMessage sendMessage = new SendMessage(callbackQuery.message().chat().id(), "Поздравляем с покупкой!");
        bot.execute(sendMessage);
        List<Product> boughtProduct = orderProductJpaRepository.findAllByOrderId(boughtOrderId)
                .stream().map(x->productJpaRepository.findById(x.getProductId()).get()).collect(Collectors.toList());
        List<Log> logList = new ArrayList<>();
        for (Product product: boughtProduct) {
            logList.add(new Log(product.getImageUrl(), product.getName(), LocalDateTime.now(),
                    materialJpaRepository.findById(product.getMaterialId()).get().getMaterial(),
                    mechTypeJpaRepository.findById(product.getMechId()).get().getType(),
                    stampJpaRepository.findById(product.getStampId()).get().getStamp()));
        }
        logJpaRepository.saveAll(logList);
        LOGGER.info("Buy confirmed");
    }
}
