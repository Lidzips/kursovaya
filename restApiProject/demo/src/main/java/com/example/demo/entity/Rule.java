package com.example.demo.entity;

public class Rule extends BaseEntity {
    private Integer alphabetFromId;
    private Integer alphabetToId;
    private Integer symbolInId;
    private Integer symbolOutId;

    public Rule(Integer id, Integer alphabetFromId, Integer alphabetToId, Integer symbolInId, Integer symbolOutId) {
        super(id);
        this.alphabetFromId = alphabetFromId;
        this.alphabetToId = alphabetToId;
        this.symbolInId = symbolInId;
        this.symbolOutId = symbolOutId;
    }

    public Integer getAlphabetFromId() {
        return alphabetFromId;
    }

    public void setAlphabetFromId(Integer alphabetFromId) {
        this.alphabetFromId = alphabetFromId;
    }

    public Integer getAlphabetToId() {
        return alphabetToId;
    }

    public void setAlphabetToId(Integer alphabetToId) {
        this.alphabetToId = alphabetToId;
    }

    public Integer getSymbolInId() {
        return symbolInId;
    }

    public void setSymbolInId(Integer symbolInId) {
        this.symbolInId = symbolInId;
    }

    public Integer getSymbolOutId() {
        return symbolOutId;
    }

    public void setSymbolOutId(Integer symbolOutId) {
        this.symbolOutId = symbolOutId;
    }
}
