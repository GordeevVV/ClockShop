package com.clockshop.service.config;


import com.clockshop.service.handlers.JdbsHandler;
import com.pengrad.telegrambot.TelegramBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TelegramConfiguration {
    static Logger LOGGER= LoggerFactory.getLogger(JdbsHandler.class);
    @Value("${shopbot.token}")
    private String SHOP_BOT_TOKEN;
    @Value("${managerbot.token}")
    private String MANAGER_BOT_TOKEN;
    @Bean("ShopBot")
    public TelegramBot getShopTelegramBot(){
        TelegramBot bot=new TelegramBot(SHOP_BOT_TOKEN);
        LOGGER.info("Shop Bot started");
        return bot;
    }
    @Bean("ManagerBot")
    public TelegramBot getManagerTelegramBot(){
        TelegramBot bot=new TelegramBot(MANAGER_BOT_TOKEN);
        LOGGER.info("Manager Bot started");
        return bot;
    }
}
