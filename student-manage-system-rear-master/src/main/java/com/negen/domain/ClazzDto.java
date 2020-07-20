package com.negen.domain;

import com.negen.entity.Clazz;
import lombok.Data;

import javax.persistence.*;

/**
 * @ Author     ：Negen
 * @ Date       ：Created in 15:34 2020/3/6
 * @ Description：班级实体
 * @ Modified By：
 * @Version: 1.0
 * 年级、班级、班主任、总人数
 */
@Data
public class ClazzDto {
    Clazz clazz;
    String price;
}
