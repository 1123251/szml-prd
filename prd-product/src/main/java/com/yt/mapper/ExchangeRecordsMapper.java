package com.yt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yt.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExchangeRecordsMapper extends BaseMapper<ExchangeRecords> {
    @Select("SELECT DATE(time) AS time, SUM(nums) AS nums\n" +
            "FROM exchange_records\n" +
            "WHERE time BETWEEN #{startTime} AND #{endTime}\n" +
            "GROUP BY DATE(time)\n" +
            "ORDER BY time;")
    List<DateRecordsVO> dateRecordsList(TimePeriod timePeriod);

    @Select("SELECT product.id AS id, product.name AS name, SUM(exchange_records.nums) as nums\n" +
            "FROM product\n" +
            "JOIN exchange_records ON product.id = exchange_records.product_id\n" +
            "WHERE exchange_records.time BETWEEN #{timePeriod.startTime} AND #{timePeriod.endTime}\n" +
            "GROUP BY product.id, product.name\n" +
            "ORDER BY nums DESC limit #{nums} ;")
    List<ProductRecordsVO> productRecords(@Param("timePeriod") TimePeriod timePeriod,@Param("nums") int nums);
    @Select("SELECT price_type as priceType, SUM(nums) as nums\n" +
            "FROM exchange_records\n" +
            "WHERE time BETWEEN #{startTime} AND #{endTime}\n" +
            "GROUP BY price_type;")
    List<PriceTypeRecordsVO> priceTypeRecords(TimePeriod timePeriod);
}
