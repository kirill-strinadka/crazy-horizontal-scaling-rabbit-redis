package com.kstrinadka.crazyhorizontalscalingrabbitredis.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CrazyTask implements Serializable {

    // todo -- зачем ребиту именно сериализуемые сообщения ????

    String id;
    String fromServer;
//    String number;

}
