package io.github.sxh705.test;

import io.github.sxh705.entity.User;
import io.github.sxh705.mapper.TestMapper;
import io.github.sxh705.mapper.UserMapper;
import io.github.sxh705.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    @Autowired
    TestMapper testMapper;

    @Test
    public void t1() {
        var a = userService.list();
        System.out.println("a = " + a);
        var b = userService.getById(1);
        System.out.println("b = " + b);
        var c = userService.lambdaQuery().eq(User::getName, "root").list();
        System.out.println("c = " + c);
        var d = userService.lambdaQuery().gt(User::getId, 0).list();
        System.out.println("d = " + d);
        var e = testMapper.execSql("select * from t_user");
        System.out.println("e = " + e);
    }
}
