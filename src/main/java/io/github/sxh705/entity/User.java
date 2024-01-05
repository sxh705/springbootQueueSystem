package io.github.sxh705.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_user")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    String name;

    String password;

    String telephone;

}
