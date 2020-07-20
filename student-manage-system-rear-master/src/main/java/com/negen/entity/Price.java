package com.negen.entity;

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
@Table(name ="tb_price")
@Entity
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Double priceAmount;
    Double discount;
    String display;
    boolean deleteStatus;
}
