package com.learn.system.service;

import com.learn.system.mapper.AccountMapper;
import com.learn.system.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountServiceImpl {
    @Autowired
    AccountMapper accountMapper;


    public int checkUsername(String username) {
        return accountMapper.checkUsername(username);
    }


    public void insertAccount(User acc) {
        accountMapper.insertAccount(acc);
    }


    public String checkPassword(String username) {
        return accountMapper.checkPassword(username);
    }


    public int getAuthority(String username){
        return accountMapper.getAuthority(username);
    }
}
