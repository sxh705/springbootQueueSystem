package io.github.sxh705.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.sxh705.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
