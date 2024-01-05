package io.github.sxh705.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    public static final Integer OK = 200;
    public static final Integer FAIL = 400;
    Integer code;
    String message;
    Object result;

}
