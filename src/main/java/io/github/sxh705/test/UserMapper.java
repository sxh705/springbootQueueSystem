package io.github.sxh705.test;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.sxh705.test.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("${sql}")
    List<Map<String, Object>> execSql(String sql);
}
