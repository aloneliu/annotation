package com.jinliu.annotation;

/**
 * @author JinLiu
 * @title
 * @date 2019/4/1 2:41 PM
 */

@Table("department")
public class Department {

    @Column("id")
    private Integer Id;

    @Column("name")
    private String Name;

    @Column("amount")
    private Integer Amount;

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

    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer amount) {
        Amount = amount;
    }
}
