package com.kstrinadka.crazyhorizontalscalingrabbitredis.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kstrinadka.crazyhorizontalscalingrabbitredis.domain.CrazyTask;
import com.kstrinadka.crazyhorizontalscalingrabbitredis.service.CrazyTaskInitializerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Component
public class CrazyTaskListener {

    ObjectMapper mapper = new ObjectMapper();

    public static final String CRAZY_TASKS_QUEUE = "kstrinadka.crazy.tasts.queue";
    public static final String CRAZY_TASKS_EXCHANGE = "kstrinadka.crazy.tasts.exchange";

    // Прием задачи
    // todo - как работает SneakyThrows (делает автоматический try-catch)
    @SneakyThrows
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = CRAZY_TASKS_QUEUE, durable = Exchange.TRUE, autoDelete = Exchange.TRUE),
                    exchange = @Exchange(value = CRAZY_TASKS_EXCHANGE, durable = Exchange.TRUE, autoDelete = Exchange.TRUE)
            )
    )
    public void handleCrazyTask(CrazyTask payload) {

        // чтобы максимум 4 задачи можно было за минуту выполнять
        Thread.sleep(15_000); // искуственная нагрузка на handler, чтобы посмотреть как задачи между серверами распределяютсял

        log.info(
                String.format(
                        "Service \"%s\" end process task \"%s\" from service \"%s\"",
                        CrazyTaskInitializerService.SERVICE_ID,
                        payload.getId(),
                        payload.getFromServer()
                )
        );

//        log.info("received message: " + mapper.writeValueAsString(payload));
    }

}
