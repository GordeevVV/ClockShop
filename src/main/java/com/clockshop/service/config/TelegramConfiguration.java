package com.clockshop.service.config;


import com.clockshop.service.handlers.JdbsHandler;
import com.pengrad.telegrambot.TelegramBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TelegramConfiguration {
    static Logger LOGGER= LoggerFactory.getLogger(JdbsHandler.class);
    private static final String BOT_TOKEN="1983895587:AAFRWEbV_HWhMRozu52TH94G5lyYSyQcitY";
    @Bean
    public TelegramBot getTelegramBot(){
        TelegramBot bot=new TelegramBot(BOT_TOKEN);
        LOGGER.info("Bot started");
        return bot;
    }
}
