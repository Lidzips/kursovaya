package com.example.demo.controller;

import com.example.demo.entity.Alphabet;
import com.example.demo.repository.AlphabetRepository;
import com.example.demo.repository.SymbolRepository;
import com.example.demo.resource.AlphabetResource;
import com.example.demo.resource.SymbolResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/alphabet")
public class AlphabetController {
    private final AlphabetRepository alphabetRepository;
    public AlphabetController(AlphabetRepository alphabetRepository) {
        this.alphabetRepository = alphabetRepository;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    AlphabetResource get(@PathVariable Integer id,
                          @RequestParam(required = false) Object expand) {
        Alphabet entity = alphabetRepository.select(id);
        if (entity == null) return null;
        return new AlphabetResource(entity);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    AlphabetResource[] getAll() {
                Alphabet[] alphabets;
                alphabets = alphabetRepository.select();
                return Arrays.stream(alphabets)
                .map(entity -> {
                    return new AlphabetResource(entity);
                })
                .toArray(AlphabetResource[]::new);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    AlphabetResource post(@RequestBody AlphabetResource resource) {
        Alphabet entity = alphabetRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new AlphabetResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    AlphabetResource put(@PathVariable Integer id,
                       @RequestBody AlphabetResource resource) {
        Alphabet entity = alphabetRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new AlphabetResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    AlphabetResource delete(@PathVariable Integer id) {
        Alphabet entity = alphabetRepository.delete(id);
        if (entity == null) return null;
        return new AlphabetResource(entity);
    }
}
