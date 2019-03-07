package com.springboot.demo01.hr.dto;

import java.io.Serializable;

public class HrUnit implements Serializable {

    private Long unitId;

    private String unitCode;

    private String unitName;

    private Long parentUnitId;

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Long getParentUnitId() {
        return parentUnitId;
    }

    public void setParentUnitId(Long parentUnitId) {
        this.parentUnitId = parentUnitId;
    }
}