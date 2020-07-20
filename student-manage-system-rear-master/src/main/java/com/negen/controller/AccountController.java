package com.negen.controller;

import com.alibaba.fastjson.JSONObject;
import com.negen.common.ServerResponse;
import com.negen.entity.Account;
import com.negen.entity.Course;
import com.negen.service.AccountService;
import com.negen.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ Author     ：Negen
 * @ Date       ：Created in 14:26 2020/3/9
 * @ Description：学生控制类
 * @ Modified By：
 * @Version: 1.0
 */
@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ServerResponse addStudent(@RequestBody Account course){
        return accountService.add(course);
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ServerResponse list(@RequestParam(defaultValue = "") String studentId){
        return accountService.list(studentId);
    }

    //查询
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public ServerResponse searchTeacher(@RequestBody JSONObject jsonObj){
        return accountService.searchCourse(jsonObj);
    }

    //查询
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public ServerResponse export(HttpServletResponse response) throws IOException {
        accountService.export(response);
        return null;
    }


}
