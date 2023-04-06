package com.example.demo.resource;

import com.example.demo.entity.Alphabet;
import com.fasterxml.jackson.annotation.JsonInclude;

public class AlphabetResource extends BaseResource {
    private Integer id;
    private String name;
    private Integer symbolId;

    public AlphabetResource() {}

    public AlphabetResource(Alphabet alphabet) {
        this.id = alphabet.getId();
        this.name = alphabet.getName();
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

    public Alphabet toEntity() {
        return new Alphabet(
                this.id,
                this.name
        );
    }
}
