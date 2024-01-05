package io.github.sxh705.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestMapper {
    @Select("${sql}")
    List<Map<String, Object>> execSql(String sql);
}
