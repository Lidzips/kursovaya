package com.example.demo.controller;

import com.example.demo.entity.Rule;
import com.example.demo.repository.AlphabetRepository;
import com.example.demo.repository.RuleRepository;
import com.example.demo.repository.SymbolRepository;
import com.example.demo.resource.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/ruleset")
public class RuleController {
    private final RuleRepository ruleRepository;
    private final AlphabetRepository alphabetRepository;
    private final SymbolRepository symbolRepository;

    public RuleController(RuleRepository ruleRepository, AlphabetRepository alphabetRepository, SymbolRepository symbolRepository) {
        this.ruleRepository = ruleRepository;
        this.alphabetRepository = alphabetRepository;
        this.symbolRepository = symbolRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    RuleResource[] getAll(@RequestParam(required = false) Object expand) {
        return Arrays.stream(ruleRepository.select())
                .map(entity -> {
                    RuleResource resource = new RuleResource(entity);
                    return resource;
                })
                .toArray(RuleResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    RuleResource get(@PathVariable Integer id,
                     @RequestParam(required = false) Object expand) {
        Rule entity = ruleRepository.select(id);
        if (entity == null) return null;
        return new RuleResource(entity);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    RuleResource post(@RequestBody RuleResource resource) {
        Rule entity = ruleRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new RuleResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    RuleResource put(@PathVariable Integer id,
                     @RequestBody RuleResource resource) {
        Rule entity = ruleRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new RuleResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    RuleResource delete(@PathVariable Integer id) {
        Rule entity = ruleRepository.delete(id);
        if (entity == null) return null;
        return new RuleResource(entity);
    }
}
