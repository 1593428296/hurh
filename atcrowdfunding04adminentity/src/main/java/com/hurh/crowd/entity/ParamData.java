package com.hurh.crowd.entity;

import java.util.List;

/**
 * @PackAgeName:com.hurh.crowd.entity
 * @ClassName:ParamData
 * @Description:
 * @Author:hrh
 * @Date:2020/4/24
 */
public class ParamData {
    private List<Integer> araay;
    public ParamData(List<Integer> araay) { this.araay = araay; }
    public ParamData() { }
    public List<Integer> getAraay() {
        return araay;
    }
    public void setAraay(List<Integer> araay) {
        this.araay = araay;
    }
}
