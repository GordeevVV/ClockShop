package com.clockshop.service.listeners;


import com.clockshop.service.entity.Manager;
import com.clockshop.service.repository.ManagerJpaRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class ManagerUpdateListener implements UpdatesListener{
    Logger logger= LoggerFactory.getLogger(ManagerUpdateListener.class);
    private TelegramBot bot;
    private ManagerJpaRepository managerJpaRepository;

    public ManagerUpdateListener(@Qualifier("ManagerBot") TelegramBot bot,ManagerJpaRepository managerJpaRepository) {
        this.bot = bot;
        this.managerJpaRepository=managerJpaRepository;
    }
    @PostConstruct
    public void postConstruct() {
        bot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> list) {
        String text;
        for (Update update:list) {
            if(update.message()!=null) {
                String prefix = "/start ";
                String token = update.message().text()
                        .substring(update.message().text().indexOf(prefix) + prefix.length());
                logger.info(update.message().text());
                Manager manager = managerJpaRepository.findByToken(token);
                if (manager != null) {
                    manager.setChatId(update.message().chat().id());
                    managerJpaRepository.save(manager);
                    text="Congratulations you are successfully authorized";
                } else {
                   text="No such manager in our company";
                }
                SendMessage sendMessage = new SendMessage(update.message().chat().id()
                        , text);
                bot.execute(sendMessage);
            }
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
