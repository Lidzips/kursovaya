package com.example.demo.entity;

public class Alphabet extends BaseEntity{
    private String name;

    public Alphabet(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
