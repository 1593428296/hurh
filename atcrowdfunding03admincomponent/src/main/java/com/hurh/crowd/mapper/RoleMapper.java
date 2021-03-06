package com.hurh.crowd.mapper;

import com.github.pagehelper.PageInfo;
import com.hurh.crowd.entity.Role;
import com.hurh.crowd.entity.RoleExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RoleMapper {

    long countByExample(RoleExample example);


    int deleteByExample(RoleExample example);


    int deleteByPrimaryKey(Integer id);


    int insert(Role record);


    int insertSelective(Role record);


    List<Role> selectByExample(RoleExample example);


    Role selectByPrimaryKey(Integer id);


    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);


    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);


    List<Role> getRolesByInputStr(String inputStr);

    List<Role> getAssignRoles(Map map);

    void saveNewrelationship(Map map);

    void deleteRelatuionShip(Map map);
}