package com.example.demo.entity;

public class Symbol extends BaseEntity{
    private String character;

    private Integer alphabetId;

    public Symbol(Integer id, String character, Integer alphabetId) {
        super(id);
        this.character = character;
        this.alphabetId = alphabetId;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public Integer getAlphabetId() {
        return alphabetId;
    }

    public void setAlphabetId(Integer alphabetId) {
        this.alphabetId = alphabetId;
    }
}
