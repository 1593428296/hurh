package com.hurh.crowd.entity;

/**
 * @ClassName: Auth
 * @Author: hurh
 * @Descriprion: 权限表
 * @Date: 2020/5/3 13:23
 * @Modifier:
 **/
public class Auth {

    private Integer id;
    private String  name;
    private String  title;
    private Integer category_id;
    private String  create_time;

    public Auth() {
    }

    @Override
    public String toString() {
        return "Auth{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", category_id=" + category_id +
                ", create_time='" + create_time + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public Auth(Integer id, String name, String title, Integer category_id, String create_time) {

        this.id = id;
        this.name = name;
        this.title = title;
        this.category_id = category_id;
        this.create_time = create_time;
    }
}
