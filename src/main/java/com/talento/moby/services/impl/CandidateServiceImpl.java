package com.talento.moby.services.impl;

import com.talento.moby.exception.BadRequestException;
import com.talento.moby.exception.ResourceNotFoundException;
import com.talento.moby.models.dto.CandidateDto;
import com.talento.moby.models.dto.CandidateWithTechnologiesDto;
import com.talento.moby.models.entities.Candidate;
import com.talento.moby.models.entities.Technology;
import com.talento.moby.repositories.CandidateRepository;
import com.talento.moby.repositories.TechnologyRepository;
import com.talento.moby.services.CandidateService;
import com.talento.moby.services.TechnologyExpertiseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.talento.moby.mappers.CandidateMapper.mapToCandidate;
import static com.talento.moby.mappers.CandidateMapper.mapToCandidateWithTechnologiesDto;

@Service
@Slf4j
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private TechnologyRepository technologyRepository;

    @Autowired
    private TechnologyExpertiseService technologyExpertiseService;

    @Override
    @Transactional
    public Candidate save(CandidateDto newCandidateDto) {
        return Optional.of(candidateRepository.save(mapToCandidate(newCandidateDto)))
                .orElseThrow(BadRequestException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Candidate getById(Long candidateId) {

        return candidateRepository.findById(candidateId).orElseThrow(() -> new ResourceNotFoundException("Candidate not found with candidateId" + candidateId));

    }

    @Override
    @Transactional
    public Candidate update(Long candidateId, CandidateDto candidateInformation) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new ResourceNotFoundException("candidate not found with candidateId " + candidateId));

        candidate.setName(candidateInformation.getName());
        candidate.setSurname(candidateInformation.getSurname());
        candidate.setDni(candidateInformation.getDni());

        return candidateRepository.save(candidate);
    }

    @Override
    @Transactional
    public void delete(Long candidateId) {
        var candidate = candidateRepository.findById(candidateId);
        candidate.ifPresentOrElse(c -> {
                    candidateRepository.deleteById(c.getId());
                },
                () -> {
                    throw new ResourceNotFoundException("Candidate not found with candidateId " + candidateId);
                });


    }

    @Override
    @Transactional(readOnly = true)
    public List<Candidate> getAll() {
        return candidateRepository.findAll(Sort.by("surname"));

    }

    @Override
    public Candidate addTechnology(Long candidateId, Long techId) {
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow(BadRequestException::new);
        Technology technology = technologyRepository.findById(techId).orElseThrow(BadRequestException::new);
        // if (!candidate.getTechnologies().contains(technology)) {

        return candidateRepository.save(candidate);
        //} else {
        //    throw new ResourceAlreadyExistsException("Candidate already has the technology " + technology);
        //}
    }

    public CandidateWithTechnologiesDto getTechnologies(Long candidateId) {
        var candidate = candidateRepository.findById(candidateId).orElseThrow(() -> {
            log.error("Candidate not found with Id: " + candidateId);
            return new ResourceNotFoundException("Candidate not found with Id: " + candidateId);
        });

        var technologies = technologyExpertiseService.getTechnologiesAndYearsOfExperienceByCandidate(candidateId);
        return mapToCandidateWithTechnologiesDto(candidate, technologies);

    }
}
