package com.example.demo.resource;

import com.example.demo.entity.Symbol;

public class SymbolResource extends BaseResource {
    private Integer id;
    private String character;

    private Integer alphabetId;

    public SymbolResource() {}

    public SymbolResource(Symbol symbol) {
        this.id = symbol.getId();
        this.character = symbol.getCharacter();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Symbol toEntity() {
        return new Symbol(
                this.id,
                this.character,
                this.alphabetId
        );
    }
}
