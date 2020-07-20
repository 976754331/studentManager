package com.negen.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@Getter
@Setter
@ToString
public class AccountVo {
    Long id;
    Long studentId;
    String type;
    Double totalAmount;
    Double usedAmount;
    Long courseId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    Date startTime;
    String studentName;
}
