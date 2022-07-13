package com.talento.moby.controllers;

import com.talento.moby.models.dto.TechnologyDto;
import com.talento.moby.models.dto.TechnologyWithCandidatesDto;
import com.talento.moby.models.entities.Technology;
import com.talento.moby.services.TechnologyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "Technologies REST Controller", tags = "REST APIs related to Technology Entity!")
public class TechnologyController {

    @Autowired
    private TechnologyService technologyService;

    @ApiOperation(value = "Get list of Technologies in the System ", response = Iterable.class)
    @GetMapping("/")
    public ResponseEntity<List<Technology>> getAll() {
        return new ResponseEntity<>(technologyService.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get one technology ", response = Technology.class)
    @GetMapping("/{id}")
    public ResponseEntity<Technology> getOne(@PathVariable("id") Long technologyId) {
        return new ResponseEntity<>(technologyService.getById(technologyId), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Save one technology in the System ", response = Technology.class)
    @PostMapping(value = "/save")
    public ResponseEntity<Technology> save(@Valid @RequestBody TechnologyDto newTechnology) {
        return new ResponseEntity<>(technologyService.save(newTechnology), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update one technology ", response = Technology.class)
    @PutMapping("/{id}")
    public ResponseEntity<Technology> update(@PathVariable("id") Long technologyId, @Valid @RequestBody TechnologyDto technologyInformation) {
        return new ResponseEntity<>(technologyService.update(technologyId, technologyInformation), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete one technology from the System ", response = Technology.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<Technology> delete(@PathVariable("id") Long technologyId) {
        return new ResponseEntity<>(technologyService.delete(technologyId), HttpStatus.OK);
    }

    @ApiOperation(value = "Obtains the candidates contained in a technology and their experience ", response = TechnologyWithCandidatesDto.class)
    @GetMapping("candidates")
    public ResponseEntity<TechnologyWithCandidatesDto> getCandidatesByTechnology(@RequestBody TechnologyDto technologyDto) {

        return new ResponseEntity<>(technologyService.getCandidates(technologyDto), HttpStatus.OK);
    }

}
