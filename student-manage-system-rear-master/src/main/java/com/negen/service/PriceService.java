package com.negen.service;

import com.negen.common.ResponseEnum;
import com.negen.common.ServerResponse;
import com.negen.entity.Clazz;
import com.negen.entity.Price;
import com.negen.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @ Author     ：Negen
 * @ Date       ：Created in 16:14 2020/3/6
 * @ Description：班级业务层
 * @ Modified By：
 * @Version: 1.0
 */
@Service
public class PriceService {

    @Autowired
    PriceRepository priceRepository;

    public ServerResponse addPrice(Price price) {
        price.setDisplay(price.getPriceAmount() +" * "+ (new BigDecimal(price.getDiscount()).divide(BigDecimal.valueOf(100),2, RoundingMode.HALF_UP)).toString());
        priceRepository.save(price);
        return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(price);
    }

    public ServerResponse listAllPrice() {
        List<Price> clazzes = priceRepository.findAll();
        return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(clazzes);
    }
}
