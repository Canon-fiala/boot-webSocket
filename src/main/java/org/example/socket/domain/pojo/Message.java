package org.example.socket.domain.pojo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author luoJ
 * @date 2023/5/26 16:23
 */
@Data
public class Message {

    private String from;

    private String to;

    private String text;

    private LocalDateTime date;


}
