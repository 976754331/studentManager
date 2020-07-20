package com.negen.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import java.util.Date;


/**
 * @ Author     ：Negen
 * @ Date       ：Created in 15:30 2020/3/6
 * @ Description：学生实体类
 * @ Modified By：
 * @Version: 1.0
 * 	姓名、年龄、性别、学号、年级、班级、家庭住址
 */
@SqlResultSetMapping(
        name = "testFind",  // 如果@Query 不指定name 会默认使用方法名
        classes = {
                @ConstructorResult(
                        targetClass = com.negen.vo.AccountExportVo.class,
                        columns = {
                        @ColumnResult( name = "studentName", type = String.class),
                        @ColumnResult( name = "type", type = String.class),
                        @ColumnResult( name = "totalAmount", type = Double.class),
                        @ColumnResult( name = "usedAmount", type = Double.class),
                        @ColumnResult( name = "createTime", type = Date.class),
                        @ColumnResult( name = "startTime", type = Date.class),
                        @ColumnResult( name = "teacherId", type = String.class),
                        @ColumnResult( name = "courseContent", type = String.class),
                        @ColumnResult( name = "classId", type = String.class),
                        @ColumnResult( name = "grade", type = String.class),
                        @ColumnResult( name = "display", type = String.class)
                }
                )
        }
)
@Getter
@Setter
@ToString
public class AccountExportVo {
    String studentName;
    String type;
    Double totalAmount;
    Double usedAmount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date startTime;
    String teacherId;
    String courseContent;
    String classId;
    String grade;
    String display;
}
