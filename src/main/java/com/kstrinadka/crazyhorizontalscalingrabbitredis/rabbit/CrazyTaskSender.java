package com.kstrinadka.crazyhorizontalscalingrabbitredis.rabbit;

import com.kstrinadka.crazyhorizontalscalingrabbitredis.domain.CrazyTask;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Component
public class CrazyTaskSender {

    // Чем отличается от RabbitTemplate (вроде RabbitTemplate более мощный инструмент, который здесь не нужен (он может очереди создавать))
    RabbitMessagingTemplate rabbitMessagingTemplate;


    // Отправка задачи
    public void sendCrazyTask(CrazyTask payload) {

        rabbitMessagingTemplate.convertAndSend(
                CrazyTaskListener.CRAZY_TASKS_EXCHANGE,
                null,
                payload
        );

    }
}
