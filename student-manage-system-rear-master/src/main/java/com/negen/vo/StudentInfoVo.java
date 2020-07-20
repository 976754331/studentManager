package com.negen.vo;

import lombok.Data;

import javax.persistence.*;

/**
 * @ Author     ：Negen
 * @ Date       ：Created in 15:30 2020/3/6
 * @ Description：学生实体类
 * @ Modified By：
 * @Version: 1.0
 * 	姓名、年龄、性别、学号、年级、班级、家庭住址
 */
@Data
public class StudentInfoVo{
    Long id;
    String name;
    Integer age;
    Integer sex;
    String num;
    String grade;
    String clazz;
    String address;
    String mobile;
    String email;
    String headTeacher;

    public StudentInfoVo(Long id, String headTeacher) {
        this.id = id;
        this.headTeacher = headTeacher;
    }

    public StudentInfoVo(Long id, String name, Integer age, Integer sex, String num, String grade, String clazz, String address, String mobile, String email, String headTeacher) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.num = num;
        this.grade = grade;
        this.clazz = clazz;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.headTeacher = headTeacher;
    }
}
