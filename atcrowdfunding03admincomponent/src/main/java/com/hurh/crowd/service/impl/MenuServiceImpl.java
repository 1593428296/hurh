package com.hurh.crowd.service.impl;

import com.hurh.crowd.entity.Menu;
import com.hurh.crowd.mapper.MenuMapper;
import com.hurh.crowd.mapper.UtilMapper;
import com.hurh.crowd.service.aop.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private UtilMapper utilMapper;

    @Override
    public List<Menu> getAll() {
        return menuMapper.getAllMenu();
    }

    @Override
    public void saveMenu(Menu menu) {
        menu.setId(utilMapper.getSeq_Menu_id());
        menuMapper.insert(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public void removeMenu(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
    }
}
