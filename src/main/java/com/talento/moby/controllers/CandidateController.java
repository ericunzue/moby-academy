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
        return new ResponseEntity<>(candidateService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<HttpStatus> save(@Valid @RequestBody CandidateDto newCandidate) {
        candidateService.save(newCandidate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidate> update(@PathVariable("id") Long id, @Valid @RequestBody CandidateDto candidateInformation) {
        return new ResponseEntity<>(candidateService.update(id, candidateInformation), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
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

    @DeleteMapping("/expertise/delete/{candidateId}/{technologyId}")
    public ResponseEntity<HttpStatus> deleteCandidateExpertise(@PathVariable("candidateId") Long candidateId, @PathVariable("technologyId") Long technologyId) {
        candidateService.deleteExpertise(candidateId, technologyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
