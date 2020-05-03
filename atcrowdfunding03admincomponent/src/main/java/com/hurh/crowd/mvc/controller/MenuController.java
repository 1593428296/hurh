package com.hurh.crowd.mvc.controller;

import com.hurh.crowd.entity.Menu;
import com.hurh.crowd.service.aop.MenuService;
import com.hurh.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

   //@ResponseBody
    @RequestMapping("/tree.json")
    public ResultEntity<Menu> getWholeTree() {
        // 获取全部的menu对象
        List<Menu> menuList = menuService.getAll();

        // 申明变量存储根节点
        Menu root = null;

        // 创建 Map 对象用来存储 id 和 Menu 对象的对应关系便于查找父节点
        Map<Integer, Menu> menuMap = new HashMap<>();

        for (Menu menu : menuList) {
            Integer pid = menu.getId();

            menuMap.put(pid, menu);
        }

        // 遍历menuList 查找根节点 组装子节点
        for (Menu menu : menuList) {
            // 如果pid为null 则为根节点
            Integer pid = menu.getPid();
            if (pid == null) {
                root = menu;
                // 只有一个根节点 找到就跳过本次循环
                continue;
            }

            // 若pid不为null 则说明有父节点 根据父节点查找menuMap中对应的menu对象
            Menu father = menuMap.get(pid);

            // 将当前节点存入父节点的children集合
            father.getChildren().add(menu);
        }
        return ResultEntity.successWithData(root);
    }

    //@ResponseBody
    @RequestMapping("/saveMenu.json")
    public ResultEntity<Menu> save(Menu menu){

        menuService.saveMenu(menu);

        return ResultEntity.successWithoutData();
    }

    //@ResponseBody
    @RequestMapping("/updateMenu.json")
    public ResultEntity<Menu> updateMenu(Menu menu){

        menuService.updateMenu(menu);

        return ResultEntity.successWithoutData();
    }

    //@ResponseBody
    @RequestMapping("/removeMenu.json")
    public ResultEntity<Menu> removeMenu(@RequestParam("id") Integer id){

        menuService.removeMenu(id);

        return ResultEntity.successWithoutData();
    }
}
