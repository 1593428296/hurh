package com.hurh.crowd.service.impl;

import com.hurh.crowd.mapper.MenuMapper;
import com.hurh.crowd.service.aop.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuMapper menuMapper;
}
