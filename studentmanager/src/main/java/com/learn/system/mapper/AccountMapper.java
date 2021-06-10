package com.learn.system.mapper;

import com.learn.system.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AccountMapper {

    //这里是关于登录的sql操作
    //查看是否username已经被注册过
    int checkUsername(String username);

    //插入账号和密码
    void insertAccount(User acc);

    //查看对应账号的密码是否正确，返回对应username的password
    String checkPassword(String username);

    //查找账号权限码
    int getAuthority(String username);

}
