package com.github.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("t_user_demo")
public class User {

    private Long id;
    private String name;
    private Integer age;
    private String email;

    @TableField(exist = false)
    private Integer count;
}