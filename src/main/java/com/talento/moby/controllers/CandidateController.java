package com.talento.moby.controllers;

import com.talento.moby.models.dto.CandidateDto;
import com.talento.moby.models.entities.Candidate;
import com.talento.moby.services.CandidateService;
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
@RequestMapping(value = "/api/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping
    public List<Candidate> getAll() {
        return candidateService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(candidateService.getOne(id), HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Candidate> save(@Valid @RequestBody CandidateDto newCandidate) {
        return new ResponseEntity<>(candidateService.save(newCandidate), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidate> update(@PathVariable("id") Long id, @Valid @RequestBody CandidateDto candidateInformation) {
        return new ResponseEntity<>(candidateService.update(id, candidateInformation), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Candidate> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(candidateService.delete(id), HttpStatus.OK);
    }
}
