package com.clockshop.service.handlers;

import com.clockshop.service.constants.MessageTypes;
import com.clockshop.service.entity.Product;
import com.clockshop.service.repository.MaterialJpaRepository;
import com.clockshop.service.repository.MechTypeJpaRepository;
import com.clockshop.service.repository.ProductJpaRepository;
import com.clockshop.service.repository.StampJpaRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(MessageTypes.BUY_TYPE_OF_MEASUREMENT)
public class MechTypeBuyListener extends JdbsHandler implements TelegramCallbackQueryHandler{
    TelegramBot bot;
    ProductJpaRepository productJpaRepository;
    MechTypeJpaRepository mechTypeJpaRepository;

    public MechTypeBuyListener(@Qualifier("ShopBot") TelegramBot bot, StampJpaRepository stampJpaRepository
            , MaterialJpaRepository materialJpaRepository, MechTypeJpaRepository mechTypeJpaRepository
            , ProductJpaRepository productJpaRepository) {
        super(bot, stampJpaRepository, materialJpaRepository, mechTypeJpaRepository, productJpaRepository);
        this.bot = bot;
        this.productJpaRepository = productJpaRepository;
        this.mechTypeJpaRepository = mechTypeJpaRepository;
    }

    @Override
    public void onCallBackQuery(CallbackQuery callbackQuery) {
        String prefix= MessageTypes.BUY_TYPE_OF_MEASUREMENT+"_";
        int insertMechId=Integer.parseInt(callbackQuery.data()
                .substring(callbackQuery.data().indexOf(prefix)+prefix.length()));
        List<Product> list=productJpaRepository.findByMechId(insertMechId);
        String typeOfCallback=MessageTypes.TYPE_OF_MEASUREMENT;
        clockDeployment(callbackQuery,list,typeOfCallback);
    }
}
