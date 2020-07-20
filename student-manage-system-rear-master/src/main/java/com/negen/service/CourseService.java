package com.negen.service;

import com.alibaba.fastjson.JSONObject;
import com.negen.common.ResponseEnum;
import com.negen.common.ServerResponse;
import com.negen.entity.*;
import com.negen.repository.AccountRepository;
import com.negen.repository.CourseRepository;
import com.negen.repository.PriceRepository;
import com.negen.repository.StudentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class CourseService {

    private static final String NUM = "1";
    private static final String NAME = "2";

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    AccountService accountService;
    @Autowired
    PriceRepository priceRepository;

    @Autowired
    StudentRespository studentRepository;

    @Autowired
    AccountRepository accountRepository;

    public ServerResponse addCourse(Course course) {
        Optional<Student> thiss = studentRepository.findById(course.getStudentId());
        Student student = thiss.get();
        course.setStudentName(student.getName());

        courseRepository.save(course);
        Account account = new Account();
        account.setStudentId(course.getStudentId());
        Course courseNow =  courseRepository.findByStudentIdAndStartTime(course.getStudentId(),course.getStartTime());
        account.setCourseId(courseNow.getId());
        Price price = priceRepository.findPriceWithStudent(course.getStudentId());
        double UsedAmount = price.getPriceAmount() * price.getDiscount()/100;
        account.setUsedAmount(UsedAmount*course.getTimeSize());
        accountService.add(account);
        return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(course);
    }

    public ServerResponse list(String studentId) {
        List<Course> list = null;
        if(StringUtils.isEmpty(studentId)){
           // PageRequest pageRequest = PageRequest.of(1 - 1,30);
            Course role = new Course();
            Example<Course> example = Example.of(role);
            //设置排序对象参数
            Sort sort = Sort.by(Sort.Direction.DESC,"startTime");
            list = courseRepository.findAll(example,sort);
            if(list!=null && list.size()>30){
                list = list.subList(0,30);
            }
        }else{
            list = courseRepository.findByStudentId(studentId);
            if(list!=null && list.size()>30){
                list = list.subList(0,30);
            }
        }
        return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(list);
    }

    public ServerResponse searchCourse(JSONObject jsonObj) {
        try {
            String select = jsonObj.getString("select");
            String content = jsonObj.getString("content");
            List<Course> students = new ArrayList<>();
            switch (select){
                case NUM:
                    students = courseRepository.findByStudentIdContaining(content);
                    break;
                case NAME:
                    students = courseRepository.findByStudentNameContaining(content);
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
            Optional<Course> courseOptional = courseRepository.findById(id);
            Course course = courseOptional.get();
            Account account = accountRepository.findByCourseId(course.getId());
            accountRepository.delete(account);
            courseRepository.deleteById(id);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_SUCCESS);
        }catch (Exception e){
            //log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }
}
