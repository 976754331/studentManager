package com.negen.controller;

import com.negen.common.ResponseEnum;
import com.negen.common.ServerResponse;
import com.negen.entity.Price;
import com.negen.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author     ：Negen
 * @ Date       ：Created in 14:26 2020/3/9
 * @ Description：学生控制类
 * @ Modified By：
 * @Version: 1.0
 */
@RestController
@RequestMapping("price")
public class PriceController {
    @Autowired
    private PriceService priceService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ServerResponse addStudent(@RequestBody Price price){
        return priceService.addPrice(price);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    ServerResponse listClazz(){
        return priceService.listAllPrice();
    }
}
