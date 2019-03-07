package com.springboot.demo01.hr.mapper;

import com.springboot.demo01.hr.dto.HrUnit;
import java.util.List;

public interface HrUnitMapper {

    int deleteByPrimaryKey(Long unitId);

    int insert(HrUnit record);

    HrUnit selectByPrimaryKey(Long unitId);

    List<HrUnit> selectAll();

    int updateByPrimaryKey(HrUnit record);
}