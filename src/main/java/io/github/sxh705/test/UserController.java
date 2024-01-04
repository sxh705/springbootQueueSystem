package io.github.sxh705.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/test")
public class UserController {
    @RequestMapping("/time")
    Object time() {
        var now = LocalDateTime.now();
        log.info("now: {}", now);
        var thread = Thread.currentThread();
        log.info("thread: {}", thread);
        return now;
    }

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @RequestMapping("exec/{sql}")
    Object exec(@PathVariable("sql") String sql) {
        var res = userMapper.execSql(sql);
        log.info("res: {}", res);
        return res;
    }

}
