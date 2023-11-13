package org.example.socket.service.impl;

import org.example.socket.domain.pojo.Staff;
import org.example.socket.mapper.LoginMapper;
import org.example.socket.service.LoginService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author luoJ
 * @date 2023/5/26 16:21
 */
@Service
@Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.DEFAULT,timeout=5)
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginMapper loginMapper;

    @Override
    public String getpwdbyname(String name) {
        Staff s=loginMapper.getpwdbyname(name);
        if(s!=null)
            return s.getPassword();
        else
            return null;
    }

    @Override
    public Long getUidbyname(String name) {
        Staff s=loginMapper.getpwdbyname(name);
        if(s!=null)
            return (long) s.getStaffId();
        else
            return null;
    }

    @Override
    public String getnamebyid(long id) {
        Staff s=loginMapper.getnamebyid(id);
        if(s!=null)
            return s.getUsername();
        else
            return null;
    }
}
