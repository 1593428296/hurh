package com.hurh.crowd.entity;

/**
 * @PackAgeName:com.hurh.crowd.entity
 * @ClassName:Address
 * @Description:
 * @Author:hrh
 * @Date:2020/4/24
 */
public class Addresses {
    private String province;
    private String city;
    private String street;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
