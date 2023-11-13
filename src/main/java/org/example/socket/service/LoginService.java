package org.example.socket.service;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author luoJ
 * @date 2023/5/26 16:21
 */
public interface LoginService {

    String getpwdbyname(String name);
    Long getUidbyname(String name);
    String getnamebyid(long id);
}
