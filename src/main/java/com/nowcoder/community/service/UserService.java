package com.nowcoder.community.service;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserById(int id){
/*        User user = getCache(id);
        if(user == null) {
            user = initCache(id);
        }
        return user;*/
        return userMapper.selectById(id);
    }

}
