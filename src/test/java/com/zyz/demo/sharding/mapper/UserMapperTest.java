package com.zyz.demo.sharding.mapper;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springmybatis.xml"})
public class UserMapperTest {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    private UserMapper userMapper;

    @Before
    public void setUp() {
        userMapper = sqlSessionFactory.openSession().getMapper(UserMapper.class);
    }

    @Test
    public void getUserByName() {
//        User user = userMapper.getUserByName("zzz");
//        System.out.println("uid: " + user.getUid() + "\nname: " + user.getUsername() + "\npwd: " + user.getPassword());
    }

    @Test
    public void addUser() {
//        User user = new User("yyy", "222");
//        boolean ok = userMapper.addUser(user);
//        System.out.println(ok);
    }
}