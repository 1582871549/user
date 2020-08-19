package com.meng.user;

import com.meng.user.common.base.ResultBase;
import com.meng.user.common.util.ResultUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class ResultUtilTests {

    @Test
    void success() {

        ArrayList<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");

        ResultBase base1 = ResultUtil.success("ssss");
        ResultBase base2 = ResultUtil.success(123);
        ResultBase base3 = ResultUtil.success(strings);

        System.out.println(base1);
        System.out.println(base2);
        System.out.println(base3);

    }

}
