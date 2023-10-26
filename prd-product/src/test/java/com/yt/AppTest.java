package com.yt;

import com.yt.controller.ProductController;
import com.yt.entity.SelectRulers;
import com.yt.mapper.ExchangeRecordsMapper;
import com.yt.mapper.ProductMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
        @Autowired
        ExchangeRecordsMapper exchangeRecordsMapper;

        @Test
        public  void testSelectRulers() throws ParseException {
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
@SpringJUnitWebConfig(
        classes = ProductController.class
)
@AutoConfigureMockMvc
@EnableWebMvc
class ProductControllerTest{
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductMapper productMapper;
    @MockBean
    ExchangeRecordsMapper exchangeRecordsMapper;


    @Test
    @SneakyThrows
    void test(){

    }


}
