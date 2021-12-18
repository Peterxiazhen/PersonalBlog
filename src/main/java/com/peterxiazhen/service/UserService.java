package com.peterxiazhen.service;

import com.peterxiazhen.domain.UserExample;
import java.util.List;
import com.peterxiazhen.domain.User;
public interface UserService{

    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    List<User> selectByExampleWithBlobs(UserExample example);

    User selectByPrimaryKey(Integer id);

    // 根据用户名来查询用户
    User getUserByUserName(String userName);

    int updateByExampleSelective(User record,UserExample example);

    int updateByExample(User record,UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int updateByPrimaryKeyWithBLOBs(User user);

}
