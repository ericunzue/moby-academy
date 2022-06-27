package com.talento.moby.controllers;

import com.talento.moby.models.dto.CandidateDto;
import com.talento.moby.models.dto.CandidateWithTechnologiesDto;
import com.talento.moby.models.entities.Candidate;
import com.talento.moby.services.CandidateService;
import com.talento.moby.services.TechnologyExpertiseService;
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
@RequestMapping(value = "/api/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private TechnologyExpertiseService technologyExpertiseService;

    @GetMapping
    public ResponseEntity<List<Candidate>> getAll() {
        return new ResponseEntity<>(candidateService.getAll(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(candidateService.getById(id), HttpStatus.ACCEPTED);
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
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        candidateService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/add/technology/{candidateId}/{technologyId}")
    public ResponseEntity<HttpStatus> addTechnology(@PathVariable("candidateId") Long candidateId, @PathVariable("technologyId") Long technologyId, @Valid @RequestBody int expertise) {
        technologyExpertiseService.save(candidateId, technologyId, expertise);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/technologies/{candidateId}")
    public ResponseEntity<CandidateWithTechnologiesDto> getTechnologies(@PathVariable("candidateId") Long candidateId) {
        return new ResponseEntity<>(candidateService.getTechnologies(candidateId), HttpStatus.OK);
    }
}
