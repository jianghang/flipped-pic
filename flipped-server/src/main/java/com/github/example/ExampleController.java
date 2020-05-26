package com.github.example;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.example.dao.UserMapper;
import com.github.example.entity.User;
import com.github.example.service.IExampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExampleController {

    private static Logger logger = LoggerFactory.getLogger(ExampleController.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    @Qualifier("exampleServiceImpl")
    private IExampleService exampleService;

    @RequestMapping("/hello")
    public String hello() {
        logger.info("hello");
        return "Hello,World!";
    }

    @GetMapping("/getAllUser")
    public List<User> getAllUser() {
        return exampleService.getAllUser();
    }

    @PostMapping("/updateUser")
    public boolean updateUser(User user){
        return true;
    }

    @GetMapping("/user/age/{pageSize}/{pageNo}")
    public Page<User> getUserByAge(@PathVariable("pageSize") Integer pageSize, @PathVariable("pageNo") Integer pageNo, @RequestParam("age") Integer age) {
        Page<User> userPage = new Page<>();
        userPage.setCurrent(pageNo);
        userPage.setSize(pageSize);

        return userPage.setRecords(userMapper.selectUserListByAge(userPage, age));
    }
}
