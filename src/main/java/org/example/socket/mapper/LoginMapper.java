package org.example.socket.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.socket.domain.pojo.Staff;
import org.springframework.stereotype.Repository;

/**
 * @author luoJ
 * @date 2023/5/26 16:20
 */
@Repository
@Mapper
public interface LoginMapper{
    Staff getpwdbyname(String name);

    Staff getnamebyid(long id);
}
