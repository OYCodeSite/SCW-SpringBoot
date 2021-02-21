package com.oy.scw;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScwUserApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    public void contextLoads() throws SQLException {

        Connection conn = dataSource.getConnection();

        System.out.println(conn); //代理对象

        conn.close();//不是销毁对象，而是归还到连接池。
    }

    @Test
    public void test2(){
        redisTemplate.opsForValue().set("key111","value111");
    }

    @Test
    public void test3(){
        String key = redisTemplate.opsForValue().get("key111");
        System.out.println(key);
    }

}
