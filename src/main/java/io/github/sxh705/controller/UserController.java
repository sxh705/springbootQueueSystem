package io.github.sxh705.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.RandomUtil;
import io.github.sxh705.entity.User;
import io.github.sxh705.service.UserService;
import io.github.sxh705.entity.Result;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/check")
    public Result check() {
        if (!StpUtil.isLogin()) {
            return new Result(Result.FAIL, "未登录", null);
        }
        return new Result(Result.OK, "已登录", List.of(
                StpUtil.getTokenInfo(),
                userService.lambdaQuery().eq(User::getId, StpUtil.getLoginId()).one()
        )
        );
    }

    @RequestMapping("/login/{username}/{password}")
    public Result login(@PathVariable String username, @PathVariable String password) {
        log.info("username: {}", username);
        log.info("password: {}", password);
        if (username == null || password == null
                || "".equals(username) || "".equals(password)) {
            return new Result(Result.FAIL, "用户名或密码为空", null);
        }
        User user = userService.lambdaQuery().eq(User::getName, username).one();
        if (user == null) {
            return new Result(Result.FAIL, "用户名不存在", null);
        }
        if (!user.getPassword().equals(password)) {
            return new Result(Result.FAIL, "用户密码错误", null);
        }
        StpUtil.login(user.getId());
        return new Result(Result.OK, "密码正确", StpUtil.getTokenInfo());
    }

    @RequestMapping("/logout")
    public Result logout() {
        if (!StpUtil.isLogin()) {
            return new Result(Result.FAIL, "注销失败, 未登录", null);
        }
        StpUtil.logout();
        return new Result(Result.OK, "注销成功", null);
    }

    @RequestMapping("/reg")
    public Result register(@RequestBody User user) {
        try {
            if ("".equals(user.getTelephone())) user.setTelephone(null);
            userService.save(user);
        } catch (Exception e) {
            return new Result(Result.FAIL, "用户保存失败,用户名重复或电话号码重复", e.getMessage());
        }
        return new Result(Result.OK, "用户注册成功", user);
    }

    @Autowired
    StringRedisTemplate redisTemplate;

    @RequestMapping("/find/code")
    public Result code(@RequestBody User user) {
        if (user == null || user.getTelephone() == null || "".equals(user.getTelephone())) {
            return new Result(Result.FAIL, "未填写手机号", null);
        } else {
            var code = RandomUtil.randomString(4).toLowerCase();
            redisTemplate.opsForValue().set("CODE_" + user.getTelephone(), code, 10 * 60, TimeUnit.SECONDS);
            log.info("code: {}", code);
            return new Result(Result.OK, "已发送验证码,十分钟内有效", code);
        }
    }

    @Data
    static class FindContext {
        String telephone;
        String code;
    }

    @RequestMapping("/find/find")
    public Result find(@RequestBody FindContext findContext) {
        User user1 = userService.lambdaQuery().eq(User::getTelephone, findContext.telephone).one();
        var code = redisTemplate.opsForValue().get("CODE_" + findContext.telephone);
        if (code == null) {
            return new Result(Result.FAIL, "未发送验证码", null);
        }
        if (code.equals(findContext.code)) {
            return new Result(Result.OK, "密码已找回", user1);
        }
        return new Result(Result.FAIL, "验证码错误", null);
    }

}
