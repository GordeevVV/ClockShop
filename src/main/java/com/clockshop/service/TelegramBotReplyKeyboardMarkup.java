package com.clockshop.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


public class TelegramBotReplyKeyboardMarkup {
    TelegramBot bot;

    public TelegramBotReplyKeyboardMarkup(TelegramBot bot) {
        this.bot = bot;
    }

    public void setReplyKeyboardMarkup(Message message,String text) {
        Keyboard keyboard = new ReplyKeyboardMarkup(
                new KeyboardButton("text"),
                new KeyboardButton("contact").requestContact(true),
                new KeyboardButton("location").requestLocation(true));
        SendMessage sendMessage=new SendMessage(message.chat().id(),"tttttt");
        sendMessage.replyMarkup(keyboard);

    }
}
