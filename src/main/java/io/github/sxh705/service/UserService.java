package io.github.sxh705.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.sxh705.entity.User;
import io.github.sxh705.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

}