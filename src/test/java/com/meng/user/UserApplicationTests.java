package com.meng.user;

import com.meng.user.common.config.ShiroProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserApplicationTests {

    @Autowired
    private ShiroProperties shiroProperties;

    @Test
    void contextLoads() {

        System.out.println("--------------------");
        System.out.println(shiroProperties.toString());
    }

}
