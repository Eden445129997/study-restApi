package com.example.study.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

@ApiModel
public class UserEntity {

    @ApiParam(value = "用户id",required = true)
    private Integer id;
    @ApiParam(value = "用户姓名")
    private String uname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

}
