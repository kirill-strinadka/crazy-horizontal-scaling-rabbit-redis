package com.kstrinadka.crazyhorizontalscalingrabbitredis.service;

import com.kstrinadka.crazyhorizontalscalingrabbitredis.config.RedisLock;
import com.kstrinadka.crazyhorizontalscalingrabbitredis.domain.CrazyTask;
import com.kstrinadka.crazyhorizontalscalingrabbitredis.rabbit.CrazyTaskSender;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class CrazyTaskInitializerService {

    public static final String SERVICE_ID = generateShortId();

    RedisLock redisLock;
    CrazyTaskSender crazyTaskSender;

    private static final long ONE_MINUTE_IN_MILLIS = 1000 * 60;
    private static final String GENERATE_CRAZY_TASKS_KEY = "crazy:horizontal:scaling:generate:crazy:tasks";  // почему-то для редиса тут двоеточия нужны


    // todo - разобраться почему лучше тут cron использовать (подводные камни при нескольких поднятых инстансах данного сервиса)
//    @Scheduled(fixedDelay = 8000L)
    @Scheduled(cron = "0/15 * * * * *")
    public void generateCrazyTasks() {

        // может пройти мимо этого оператора, если не крону использовать ???
        if (redisLock.acquireLock(ONE_MINUTE_IN_MILLIS, GENERATE_CRAZY_TASKS_KEY)) {

            log.info(Strings.repeat("-", 100));
            log.info(String.format("Server \"%s\" starts generate tasks", SERVICE_ID));

            for (int i = 0; i < 5; i++) {
                crazyTaskSender.sendCrazyTask(
                        CrazyTask.builder()
                                .id(generateShortId())
                                .fromServer(SERVICE_ID)
                                .build()
                );
            }

            log.info(String.format("Server \"%s\" stops generate tasks", SERVICE_ID));
            log.info(Strings.repeat("-", 100));

            redisLock.releaseLock(GENERATE_CRAZY_TASKS_KEY);
        }


    }

    private static String generateShortId() {
        return UUID.randomUUID().toString().substring(0, 4);
    }
}
