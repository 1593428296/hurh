package com.hurh.crowd.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @PackAgeName:com.hurh.crowd.mapper
 * @ClassName:UtilMapper
 * @Description:
 * @Author:hrh
 * @Date:2020/4/23
 */
public interface UtilMapper {

    /**
     * 获取用户-角色表的序列
     * @Param:
     * @return:
     */
    Integer getSeq_inner_admin_role_id();

    /**
     * 获取角色表的序列
     * @Param:
     * @return:
     */
    Integer getSeq_Role_id();

    /**
     * 获取用户表的序列
     * @Param:
     * @return:
     */
    String getSeq_T_admin_id();

    /**
     * 获取菜单表序列
     * @Param: []
     * @return: java.util.List<com.hurh.crowd.entity.Menu>
     */
    Integer getSeq_Menu_id();
}
