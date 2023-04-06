package com.example.demo.controller;

import com.example.demo.entity.Alphabet;
import com.example.demo.entity.Symbol;
import com.example.demo.repository.AlphabetRepository;
import com.example.demo.repository.SymbolRepository;
import com.example.demo.resource.SymbolResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/symbol")
public class SymbolController {
    private final SymbolRepository symbolRepository;

    private final AlphabetRepository alphabetRepository;

    public SymbolController(SymbolRepository symbolRepository, AlphabetRepository alphabetRepository) {
        this.symbolRepository = symbolRepository;
        this.alphabetRepository = alphabetRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    SymbolResource[] getAll(@RequestParam(required = false) Object expand) {
        return Arrays.stream(symbolRepository.select())
                .map(SymbolResource::new)
                .toArray(SymbolResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    SymbolResource get(@PathVariable Integer id,
                          @RequestParam(required = false) Object expand) {
        Symbol entity = symbolRepository.select(id);
        if (entity == null) return null;
        return new SymbolResource(entity);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    SymbolResource post(@RequestBody SymbolResource resource) {
        Symbol entity = symbolRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new SymbolResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    SymbolResource put(@PathVariable Integer id,
                          @RequestBody SymbolResource resource) {
        Symbol entity = symbolRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new SymbolResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    SymbolResource delete(@PathVariable Integer id) {
        Symbol entity = symbolRepository.delete(id);
        if (entity == null) return null;
        return new SymbolResource(entity);
    }

    @GetMapping("/transliterate")
    public ResponseEntity<String> transliterateSymbol(
            @RequestParam("character") String character,
            @RequestParam("alphabet1") String fromAlphabetName,
            @RequestParam("alphabet2") String toAlphabetName
    ) {
        Integer alphabetId1 = alphabetRepository.selectIdByName(fromAlphabetName);
        Integer alphabetId2 = alphabetRepository.selectIdByName(toAlphabetName);
        String result = symbolRepository.selectByRule(character, fromAlphabetName, toAlphabetName);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

}
