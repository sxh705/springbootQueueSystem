package io.github.sxh705.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

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
        var e = userMapper.execSql("select * from t_user");
        System.out.println("e = " + e);
    }
}
