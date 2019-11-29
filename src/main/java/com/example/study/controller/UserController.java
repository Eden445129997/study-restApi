package com.example.study.controller;

import com.example.study.dao.UserDao;
import com.example.study.entity.UserEntity;
import com.example.study.singleton.SingletonMybatis;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//声明这个一个rest风格的controller层
@RequestMapping("/user")//路由
@Api(tags = "用户接口")
public class UserController {
    //单例设计——生产连接数据库实例的工厂
    private static SqlSessionFactory sqlSessionFactory;
    static {
        sqlSessionFactory = SingletonMybatis.getSqlSessionFactory();
    }

    /**
     * 两种写法
     * @RequestMapping(method = RequestMethod.GET,value = "/queryUsers")
     * @GetMapping(value = "/queryUsers")
     */

    @GetMapping(value = "/queryUsers")
    @ApiOperation(value = "查询所有的用户")
    public List<UserEntity> queryUser(){
        //打开数据库连接
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //返回数组
        List<UserEntity> result = null;

        //通过反射机制获取获取接口
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        //将接口查询到的数据返回给result
        result = userDao.queryUsers();
        //提交sql
        sqlSession.commit();
        //关闭数据库连接
        sqlSession.close();
        return result;
    }

    @GetMapping(value = "/queryUserById/{id}")
    @ApiOperation(value = "通过id查询用户")
    public UserEntity queryUserById(@PathVariable("id") int id){
        //打开数据库连接
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //返回user对象
        UserEntity result = null;

        //通过反射机制获取获取接口
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        try {
            //将接口查询到的数据返回给result
            result = userDao.queryUserById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        //提交sql
        sqlSession.commit();
        //关闭数据库连接
        sqlSession.close();
        return result;
    }

    @PostMapping(value = "/addUser")
    @ApiOperation(value = "添加用户")
    public boolean addUser(@RequestBody UserEntity user){
        //打开数据库连接
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //返回布尔值
        boolean result = true;

        //通过反射机制获取获取接口
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        try {
            //将接口查询到的数据返回给result
            result = userDao.addUser(user);
        }catch (Exception e){
            e.printStackTrace();
            result = false;
        }

        //提交sql
        sqlSession.commit();
        //关闭数据库连接
        sqlSession.close();
        return result;
    }



}