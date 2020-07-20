package com.negen.controller;

import com.alibaba.fastjson.JSONObject;
import com.negen.common.ServerResponse;
import com.negen.entity.Course;
import com.negen.entity.Score;
import com.negen.service.CourseService;
import com.negen.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ Author     ：Negen
 * @ Date       ：Created in 14:26 2020/3/9
 * @ Description：学生控制类
 * @ Modified By：
 * @Version: 1.0
 */
@RestController
@RequestMapping("score")
public class ScoreController {
    @Autowired
    ScoreService scoreService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ServerResponse addStudent(@RequestBody Score course){
        return scoreService.addCourse(course);
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ServerResponse list(@RequestParam(defaultValue = "") String studentId){
        return scoreService.list(studentId);
    }

    //查询
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public ServerResponse searchTeacher(@RequestBody JSONObject jsonObj){
        return scoreService.searchCourse(jsonObj);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    public ServerResponse deleteStudent(@PathVariable Long id){
        return scoreService.deleteCourse(id);
    }

}
