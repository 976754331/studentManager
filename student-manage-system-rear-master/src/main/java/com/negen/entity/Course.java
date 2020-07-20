package com.negen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


/**
 * @ Author     ：Negen
 * @ Date       ：Created in 15:30 2020/3/6
 * @ Description：学生实体类
 * @ Modified By：
 * @Version: 1.0
 * 	姓名、年龄、性别、学号、年级、班级、家庭住址
 */
@Data
@Table(name ="tb_course")
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long studentId;
    String teacherId;
    String classId;
    String studentName;
    String courseContent;
    Integer timeSize;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    Date endTime;
}
