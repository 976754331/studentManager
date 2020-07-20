package com.negen.service.impl;

import com.negen.common.ResponseEnum;
import com.negen.common.ServerResponse;
import com.negen.domain.ClazzDto;
import com.negen.entity.ClassPrice;
import com.negen.entity.Clazz;
import com.negen.entity.Student;
import com.negen.repository.ClassPriceRepository;
import com.negen.repository.ClazzRepository;
import com.negen.repository.StudentRespository;
import com.negen.service.IClazzService;
import com.negen.vo.ClazzInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ClazzService implements IClazzService {
    @Autowired
    ClazzRepository clazzRepository;

    @Autowired
    ClassPriceRepository classPriceRepository;

    @Autowired
    StudentRespository studentRespository;

    @Override
    public ServerResponse addClazz(ClazzDto clazzDto) {
        try {
            Clazz clazz = clazzDto.getClazz();
            String grade = clazz.getGrade();
            String clazzName = clazz.getClazz();
            if(StringUtils.isEmpty(grade)
            || StringUtils.isEmpty(clazzName))
                return ServerResponse.getInstance().responseEnum(ResponseEnum.INVALID_PARAM);
            if(null != clazzRepository.findByGradeAndClazz(grade, clazzName))
                return ServerResponse.getInstance().responseEnum(ResponseEnum.CLAZZ_EXSIT);
            clazzRepository.save(clazz);

            Clazz create = clazzRepository.findByGradeAndClazz(grade, clazzName);
            ClassPrice classPrice = new ClassPrice();
            classPrice.setClassId(create.getId());
            classPrice.setPriceId(Long.parseLong(clazzDto.getPrice()));
            classPriceRepository.save(classPrice);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.ADD_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    @Override
    public ServerResponse listAllClazz() {
        try {
            List<Clazz> clazzes = clazzRepository.findAll();
            if(null == clazzes)
                return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(new ArrayList<>());
            ArrayList<ClazzInfoVo> clazzInfoVos = new ArrayList<>();
            clazzes.forEach(item -> {
                ClazzInfoVo clazzInfoVo = new ClazzInfoVo();
                clazzInfoVo.setId(item.getId());
                clazzInfoVo.setGrade(item.getGrade());
                clazzInfoVo.setClazz(item.getClazz());
                clazzInfoVo.setHeadTeacher(item.getHeadTeacher());
                clazzInfoVo.setTotalStudent(item.getTotalStudent());
                clazzInfoVo.setCurrentTotalStudent(studentRespository.findByGradeAndClazz(item.getGrade(),
                        item.getClazz()).size());
                clazzInfoVos.add(clazzInfoVo);
            });
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(clazzInfoVos);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }


    @Override
    public ServerResponse updateClazz(ClazzDto clazzDto) {
        try {
            Clazz clazz = clazzDto.getClazz();
            Clazz oldClazz = clazzRepository.findById(clazz.getId()).get();
            oldClazz.setGrade(clazz.getGrade());
            oldClazz.setClazz(clazz.getClazz());
            oldClazz.setHeadTeacher(clazz.getHeadTeacher());
            oldClazz.setCurrentTotalStudent(clazz.getCurrentTotalStudent());
            clazzRepository.save(oldClazz);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_SUCCESS);
        }catch (Exception e){
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }


    @Override
    public ServerResponse deleteClazz(Long id) {
        try {
            //检测班级下是否有学生
            Clazz clazz = clazzRepository.getOne(id);
            List<Student> students =
                    studentRespository.findByGradeAndClazz(clazz.getGrade(), clazz.getClazz());
            if(students.size() > 0)
                return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_FIALED);
            clazzRepository.deleteById(id);
            //删除成功
            return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_SUCCESS);
        }catch (Exception e){
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }


    @Override
    public ServerResponse getAllGrade() {
        try {
            List<String> grades = clazzRepository.selectGrades();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(grades);
        }catch (Exception e ) {
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    @Override
    public ServerResponse getAllClazz(String grade) {
        try {
            if(StringUtils.isEmpty(grade))
                return ServerResponse.getInstance().responseEnum(ResponseEnum.INVALID_PARAM);
            List<String> clazzs = clazzRepository.selectClazzs(grade);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(clazzs);
        }catch (Exception e ) {
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }
}
