package com.talento.moby.controllers;

import com.talento.moby.models.dto.TechnologyDto;
import com.talento.moby.models.entities.Technology;
import com.talento.moby.services.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/technologies")
public class TechnologyController {

    @Autowired
    private TechnologyService technologyService;

    @GetMapping
    public List<Technology> getAll() {
        return technologyService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Technology> getOne(@PathVariable("id") Long technologyId) {
        return new ResponseEntity<>(technologyService.getOne(technologyId), HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Technology> save(@Valid @RequestBody TechnologyDto newTechnology) {
        return new ResponseEntity<>(technologyService.save(newTechnology), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Technology> update(@PathVariable("id") Long technologyId, @Valid @RequestBody TechnologyDto technologyInformation) {
        return new ResponseEntity<>(technologyService.update(technologyId, technologyInformation), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Technology> delete(@PathVariable("id") Long technologyId) {
        return new ResponseEntity<>(technologyService.delete(technologyId), HttpStatus.OK);
    }

}
