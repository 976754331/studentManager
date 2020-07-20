package com.negen.service;

import com.alibaba.fastjson.JSONObject;
import com.negen.common.ResponseEnum;
import com.negen.common.ServerResponse;
import com.negen.entity.*;
import com.negen.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @ Author     ：Negen
 * @ Date       ：Created in 16:14 2020/3/6
 * @ Description：班级业务层
 * @ Modified By：
 * @Version: 1.0
 */
@Service
public class ScoreService {

    private static final String NUM = "1";
    private static final String NAME = "2";

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    StudentRespository studentRepository;

    public ServerResponse addCourse(Score course) {
        Optional<Student> thiss = studentRepository.findById(course.getStudentId());
        Student student = thiss.get();
        course.setStudentName(student.getName());

        scoreRepository.save(course);
        return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(course);
    }

    public ServerResponse list(String studentId) {
        List<Score> list = null;
        if(StringUtils.isEmpty(studentId)){
           // PageRequest pageRequest = PageRequest.of(1 - 1,30);
            Score role = new Score();
            Example<Score> example = Example.of(role);
            //设置排序对象参数
            Sort sort = Sort.by(Sort.Direction.DESC,"examTime");
            list = scoreRepository.findAll(example,sort);
            if(list!=null && list.size()>30){
                list = list.subList(0,30);
            }
        }else{

        }
        return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(list);
    }

    public ServerResponse searchCourse(JSONObject jsonObj) {
        try {
            String select = jsonObj.getString("select");
            String content = jsonObj.getString("content");
            List<Score> students = new ArrayList<>();
            switch (select){
                case NUM:
                    students = scoreRepository.findByStudentIdContaining(content);
                    break;
                case NAME:
                    students = scoreRepository.findByStudentNameContaining(content);
                    break;
                default:
                    break;
            }
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(students);

        }catch (Exception e){
            //log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    public ServerResponse deleteCourse(Long id) {
        try {
            scoreRepository.deleteById(id);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_SUCCESS);
        }catch (Exception e){
            //log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }
}
