package com.negen.service;

import com.alibaba.fastjson.JSONObject;
import com.negen.common.ResponseEnum;
import com.negen.common.ServerResponse;
import com.negen.entity.Account;
import com.negen.entity.Course;
import com.negen.entity.Price;
import com.negen.entity.Student;
import com.negen.repository.AccountRepository;
import com.negen.repository.CourseRepository;
import com.negen.repository.PriceRepository;
import com.negen.repository.StudentRespository;
import com.negen.vo.AccountExportVo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.query.spi.NativeQueryImplementor;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ Author     ：Negen
 * @ Date       ：Created in 16:14 2020/3/6
 * @ Description：班级业务层
 * @ Modified By：
 * @Version: 1.0
 */
@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    StudentRespository studentRepository;

    public ServerResponse add(Account course) {
        Account lastAccount = accountRepository.findLastAccount(course.getStudentId());
        double totalAmount = 0;
        if(lastAccount != null){
            totalAmount = lastAccount.getTotalAmount();
        }
        //0 缴费  1扣费
        if("0".equals(course.getType())){
            totalAmount = totalAmount + course.getUsedAmount();
        }else{
            totalAmount = totalAmount - course.getUsedAmount();
            course.setType("1");
            Optional<Course> thisCourse = courseRepository.findById(course.getCourseId());
            Course course1 = thisCourse.get();
            course.setStartTime(course1.getStartTime());
        }
        Optional<Student> thiss = studentRepository.findById(course.getStudentId());
        Student student = thiss.get();
        course.setStudentName(student.getName());
        course.setCreateTime(new Date());
        course.setTotalAmount(totalAmount);
        accountRepository.save(course);
        return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(course);
    }

    public ServerResponse list(String studentId) {
        List<Account> list = null;
        if(StringUtils.isEmpty(studentId)){
            // PageRequest pageRequest = PageRequest.of(1 - 1,30);
            Account role = new Account();
            Example<Account> example = Example.of(role);
            //设置排序对象参数
            Sort sort = Sort.by(Sort.Direction.DESC,"createTime");
            list = accountRepository.findAll(example,sort);
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
            List<Account> students = new ArrayList<>();
            students = accountRepository.findByStudentNameContaining(content);

            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(students);

        }catch (Exception e){
            //log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        //List<AccountExportVo> allAccount = accountRepository.findAllAndExport();
        Query nativeQuery = entityManager.createNativeQuery("SELECT\n" +
                "\ta.student_name,\n" +
                "\ta.type,\n" +
                "\ta.total_amount,\n" +
                "\ta.used_amount,\n" +
                "a.create_time,\n" +
                "a.start_time,\n" +
                "c.teacher_id,\n" +
                "c.course_content,\n" +
                "c.class_id,\n" +
                "cc.grade,\n" +
                "pp.display\n" +
                "FROM\n" +
                "\ttb_account a\n" +
                "LEFT JOIN tb_course c ON a.course_id = c.id\n" +
                "LEFT JOIN tb_clazz cc ON c.class_id = cc.clazz\n" +
                "LEFT JOIN tb_class_price cp ON cp.class_id = cc.id\n" +
                "LEFT JOIN tb_price pp ON pp.id = cp.price_id\n" +
                "ORDER BY\n" +
                "\ta.create_time DESC");
        //不同hibernate版本写法不一样
        NativeQueryImplementor nativeQueryImplementor = nativeQuery
                .unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map> resultList = nativeQueryImplementor.getResultList();
        System.out.println("===============" + resultList);
        List<List<String>> accounts = new ArrayList<>();
        SimpleDateFormat sf = new SimpleDateFormat("MM-dd_HH-mm");
        for(Map vo:resultList){
            List<String> account = new ArrayList<>();
            account.add(vo.get("student_name")+"");
            account.add("1".equals(vo.get("type"))?"消费":"缴费");
            account.add(vo.get("total_amount")+"");
            account.add(("1".equals(vo.get("type"))?"-":"+")+vo.get("used_amount"));
            account.add(vo.get("create_time")+"");
            account.add(vo.get("start_time") == null?"":vo.get("start_time")+"");
            account.add(vo.get("teacher_id") == null?"":vo.get("teacher_id")+"");
            account.add(vo.get("course_content") == null?"":vo.get("course_content")+"");
            account.add(vo.get("class_id") == null?"":vo.get("class_id")+"");
            account.add(vo.get("grade") == null?"":vo.get("grade")+"");
            account.add(vo.get("display") == null?"":vo.get("display")+"");
            accounts.add(account);
        }
        String[] title = {"学生姓名", "缴费类型", "现有总额", "消费金额", "消/缴费时间", "上课时间", "教师", "上课内容", "班级", "年级", "单价"};
        String fileName = "D:/account_"+sf.format(new Date())+".xls";
        File file = new File(fileName);
        HSSFWorkbook wb = getHSSFWorkbook(title, accounts, null);
        //响应到客户端
       /* setResponseHeader(response, "账单明细信息_" + sf.format(new Date()) + ".xls");
        OutputStream os = response.getOutputStream();*/
        if (file.exists()) {
            file.delete();
        }
        //将文件保存到指定的位置
        file.createNewFile();
        wb.write(file);
        /*wb.write(os);
        os.flush();
        os.close();*/
        wb.close();
        System.out.println("=============== all is ok");
    }


    /**
     * 导出Excel
     * @param title 标题
     * @param values 内容
     * @param wb HSSFWorkbook对象
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String []title, List<List<String>> values, HSSFWorkbook wb){

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("导出账单及明细");

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
//        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //创建内容
        for(int i=0;i<values.size();i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values.get(i).size();j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values.get(i).get(j));
            }
        }
        return wb;
    }

    //发送响应流方法
    public static void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"utf-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
