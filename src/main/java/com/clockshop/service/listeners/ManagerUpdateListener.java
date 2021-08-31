package com.clockshop.service.listeners;

import com.clockshop.service.constants.MessageTypes;
import com.clockshop.service.entity.Manager;
import com.clockshop.service.repository.ManagerJpaRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

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
        for (Update update:list) {
            String prefix= "/start ";
             String token=update.message().text()
                    .substring(update.message().text().indexOf(prefix)+prefix.length());
            logger.info(update.message().text());
            //message().text="/start {TOKEN}"
            Manager manager=managerJpaRepository.findByToken(token);
            if(manager!=null){
                manager.setChatId(update.message().chat().id());
                managerJpaRepository.save(manager);
                SendMessage sendMessage=new SendMessage(update.message().chat().id()
                        ,"Congratulations you are successfully authorized");
                bot.execute(sendMessage);
            }else{
                SendMessage sendMessage=new SendMessage(update.message().chat().id()
                        ,"No such manager in our company");
                bot.execute(sendMessage);
            }
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
