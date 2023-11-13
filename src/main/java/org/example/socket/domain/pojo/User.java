package org.example.socket.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luoJ
 * @date 2023/5/26 16:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long uid;

    private String name;
}
