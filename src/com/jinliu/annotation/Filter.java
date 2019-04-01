package com.jinliu.annotation;

/**
 * @author JinLiu
 * @title
 * @date 2019/4/1 2:41 PM
 */

@Table("user")
public class Filter {

    @Column("id")
    private Integer Id;

    @Column("name")
    private String Name;

    @Column("full_name")
    private String FullName;

    @Column("email")
    private String Email;

    @Column("age")
    private Integer Age;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }
}
