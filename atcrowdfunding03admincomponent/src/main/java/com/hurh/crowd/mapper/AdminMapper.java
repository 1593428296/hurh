package com.hurh.crowd.mapper;

import com.hurh.crowd.entity.Admin;
import com.hurh.crowd.entity.AdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(String id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin getSeq_T_admin_id();
    /*
     * @Author: hrh
     * @Description: 获取用户
     * @Date: 2020/4/26 21:52
     * @Param: []
     * @return java.util.List<com.hurh.crowd.entity.Admin>
     **/
    List<Admin> getAdminByKeyWord(String inputStr);
}