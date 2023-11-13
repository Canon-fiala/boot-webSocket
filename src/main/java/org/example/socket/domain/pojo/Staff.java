package org.example.socket.domain.pojo;

import lombok.Data;

/**
 * @author luoJ
 * @date 2023/5/26 16:24
 */
@Data
public class Staff {

    private byte staffId;
    private String firstName;
    private String lastName;
    private short addressId;
    private String email;
    private String username;
    private String password;
    private String lastUpdate;
}
