package com.example.demo.resource;

import com.example.demo.entity.Rule;
import com.fasterxml.jackson.annotation.JsonInclude;

public class RuleResource extends BaseResource {
    private Integer id;
    private Integer alphabetFromId;
    private Integer alphabetToId;
    private Integer symbolInId;
    private Integer symbolOutId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AlphabetResource alphabetFrom;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AlphabetResource alphabetTo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SymbolResource symbolIn;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SymbolResource symbolOut;

    public RuleResource() {}

    public RuleResource(Rule ruleSet) {
        this.id = ruleSet.getId();
        this.alphabetFromId = ruleSet.getAlphabetFromId();
        this.alphabetToId = ruleSet.getAlphabetToId();
        this.symbolInId = ruleSet.getSymbolInId();
        this.symbolOutId = ruleSet.getSymbolOutId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public AlphabetResource getAlphabetFrom() {
        return alphabetFrom;
    }

    public void setAlphabetFrom(AlphabetResource alphabetFrom) {
        this.alphabetFrom = alphabetFrom;
    }

    public AlphabetResource getAlphabetTo() {
        return alphabetTo;
    }

    public void setAlphabetTo(AlphabetResource alphabetTo) {
        this.alphabetTo = alphabetTo;
    }

    public SymbolResource getSymbolIn() {
        return symbolIn;
    }

    public void setSymbolIn(SymbolResource symbolIn) {
        this.symbolIn = symbolIn;
    }

    public SymbolResource getSymbolOut() {
        return symbolOut;
    }

    public void setSymbolOut(SymbolResource symbolOut) {
        this.symbolOut = symbolOut;
    }

    public Rule toEntity() {
        return new Rule(
                this.id,
                this.alphabetFromId,
                this.alphabetToId,
                this.symbolInId,
                this.symbolOutId
        );
    }
}
