package com.yt;

import com.yt.entity.SelectRulers;
import com.yt.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Unit test for simple App.
 */
@SpringBootTest(classes = {ProductApp.class})
public class AppTest
{
    @Autowired
    ProductMapper productMapper;
        @Test
      public  void test() throws ParseException {
            SelectRulers selectRulers= new SelectRulers();
            //selectRulers.setId(1);
            selectRulers.setName("商品");
            Date date = new Date();
            System.out.println(date);
            //Fri Nov 29 10:05:00 CST 2019
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            selectRulers.setStartTime(sdf.parse("2009-12-31 00:00:00"));
            selectRulers.setEndTime(sdf.parse("2025-12-31 00:00:00"));
            System.out.println(productMapper.selectByRulers(selectRulers));
        }
}
