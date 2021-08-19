package com.clockshop.service.handlers;

import com.pengrad.telegrambot.model.CallbackQuery;

public interface TelegramCallbackQueryHandler {
    void onCallBackQuery(CallbackQuery callbackQuery);
}
