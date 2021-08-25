package com.clockshop.service.handlers;

import com.clockshop.service.MessageTypes;
import com.clockshop.service.entity.Product;
import com.clockshop.service.repository.MaterialJpaRepository;
import com.clockshop.service.repository.MechTypeJpaRepository;
import com.clockshop.service.repository.ProductJpaRepository;
import com.clockshop.service.repository.StampJpaRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component(MessageTypes.BUY_MATERIAL)
public class MaterialBuyListener extends JdbsHandler implements TelegramCallbackQueryHandler{
    TelegramBot bot;
    ProductJpaRepository productJpaRepository;
    MaterialJpaRepository materialJpaRepository;

    public MaterialBuyListener(TelegramBot bot, StampJpaRepository stampJpaRepository, MaterialJpaRepository materialJpaRepository
            , MechTypeJpaRepository mechTypeJpaRepository, ProductJpaRepository productJpaRepository) {
        super(bot, stampJpaRepository, materialJpaRepository, mechTypeJpaRepository, productJpaRepository);
        this.bot = bot;
        this.productJpaRepository = productJpaRepository;
        this.materialJpaRepository = materialJpaRepository;
    }

    @Override
    public void onCallBackQuery(CallbackQuery callbackQuery) {
        String prefix=MessageTypes.BUY_MATERIAL+"_";
        int insertMaterialId=Integer.parseInt(callbackQuery.data()
                .substring(callbackQuery.data().indexOf(prefix)+prefix.length()));
        List<Product> list=productJpaRepository.findByMaterialId(insertMaterialId);
        String typeOfCallback=MessageTypes.CORP_MATERIAL;
        clockDeployment(callbackQuery,list,typeOfCallback);
    }
}
