package io.github.sxh705.test;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.sxh705.test.User;
import io.github.sxh705.test.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

}