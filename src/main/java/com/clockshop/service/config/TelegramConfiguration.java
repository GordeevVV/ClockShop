package com.clockshop.service.config;


import com.pengrad.telegrambot.TelegramBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TelegramConfiguration {
    private static final String BOT_TOKEN="1983895587:AAFRWEbV_HWhMRozu52TH94G5lyYSyQcitY";
    @Bean
    public TelegramBot getTelegramBot(){
        TelegramBot bot=new TelegramBot(BOT_TOKEN);
        return bot;
    }
}
