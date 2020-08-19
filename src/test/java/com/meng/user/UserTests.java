package com.meng.user;

import com.meng.user.service.system.UserService;
import com.meng.user.service.system.entity.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {

        System.out.println("--------------------");

        UserDTO user = userService.getUser(1L);

        System.out.println(user.toString());
    }


    @Test
    void updatePassword() {

        System.out.println("--------------------");

        UserDTO userDTO = new UserDTO();

        boolean flag = userService.updatePassword(userDTO);

        System.out.println(flag);
    }

    @Test
    void saveUser() {

        System.out.println("--------------------");

        UserDTO userDTO = new UserDTO();

        userDTO.setUsername("mengli");
        userDTO.setPassword("123456");

        boolean flag = userService.saveUser(userDTO);

        System.out.println(flag);
    }
}
