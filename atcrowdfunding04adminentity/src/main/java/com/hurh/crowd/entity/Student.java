package com.hurh.crowd.entity;

import java.util.List;
import java.util.Map;

/**
 * @PackAgeName:com.hurh.crowd.entity
 * @ClassName:Student
 * @Description:
 * @Author:hrh
 * @Date:2020/4/24
 */
public class Student {
    private Integer stuid;
    private String name;
    private Addresses addresses;
    private List<Subject> subjectList;
    private Map<String, String> map;

    public Student(Integer stuid, String name, Addresses addresses, List<Subject> subjectList, Map<String, String> map) {
        this.stuid = stuid;
        this.name = name;
        this.addresses = addresses;
        this.subjectList = subjectList;
        this.map = map;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuid=" + stuid +
                ", name='" + name + '\'' +
                ", addresses=" + addresses +
                ", subjectList=" + subjectList +
                ", map=" + map +
                '}';
    }

    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Addresses getAddresses() {
        return addresses;
    }

    public void setAddresses(Addresses addresses) {
        this.addresses = addresses;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
