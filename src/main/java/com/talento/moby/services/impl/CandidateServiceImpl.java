package com.talento.moby.services.impl;

import com.talento.moby.exception.BadRequestException;
import com.talento.moby.exception.ResourceNotFoundException;
import com.talento.moby.models.dto.CandidateDto;
import com.talento.moby.models.dto.CandidateWithTechnologiesDto;
import com.talento.moby.models.entities.Candidate;
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

    private static final String CANDIDATE_NOT_FOUND = "Candidate not found with Id";

    @Override
    @Transactional
    public Candidate save(CandidateDto newCandidateDto) {
        return Optional.of(candidateRepository.save(mapToCandidate(newCandidateDto)))
                .orElseThrow(() -> {
                    log.error("Save candidate error--> " + newCandidateDto);
                    return new BadRequestException();

                });
    }

    @Override
    @Transactional(readOnly = true)
    public Candidate getById(Long candidateId) {

        return candidateRepository.findById(candidateId).orElseThrow(() -> {
            log.error("Candidate not found");
            return new ResourceNotFoundException("Candidate not found with candidateId" + candidateId);
        });

    }

    @Override
    @Transactional
    public Candidate update(Long candidateId, CandidateDto candidateInformation) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new ResourceNotFoundException("candidate not found with candidateId " + candidateId));

        log.debug("CandidateInformation--> " + candidateInformation);
        candidate.setName(candidateInformation.getName());
        candidate.setSurname(candidateInformation.getSurname());
        candidate.setBirthDate(candidateInformation.getBirthDate());
        candidate.setDni(candidateInformation.getDni());
        return candidateRepository.save(candidate);
    }

    @Override
    @Transactional
    public void delete(Long candidateId) {
        log.debug("CandidateId--> " + candidateId);
        var candidate = candidateRepository.findById(candidateId);
        log.debug("Candidate --> " + candidate);
        candidate.ifPresentOrElse(c -> candidateRepository.deleteById(c.getId()),
                () -> {
                    throw new ResourceNotFoundException("Candidate not found with candidateId " + candidateId);
                });
    }

    @Override
    @Transactional(readOnly = true)
    public List<Candidate> getAll() {
        return Optional.of(candidateRepository.findAll(Sort.by("surname"))).orElse(null);

    }

    @Transactional(readOnly = true)
    public CandidateWithTechnologiesDto getTechnologies(Long candidateId) {
        var candidate = candidateRepository.findById(candidateId).orElseThrow(() -> {
            log.error(CANDIDATE_NOT_FOUND + candidateId);
            return new ResourceNotFoundException("Candidate not found with Id: " + candidateId);
        });
        log.debug("Candidate--> " + candidate);
        var technologies = technologyExpertiseService.getTechnologiesAndYearsOfExperienceByCandidate(candidateId);
        log.debug(String.valueOf(candidate), technologies);
        return mapToCandidateWithTechnologiesDto(candidate, technologies);
    }

    @Override
    @Transactional
    public void deleteExpertise(Long candidateId, Long technologyId) {
        var technology = Optional.of(technologyRepository.getReferenceById(technologyId))
                .orElseThrow(() -> {
                    log.error("Technology not found with Id: " + technologyId);
                    return new ResourceNotFoundException("Technology not found with ID: " + technologyId);
                });
        var candidate = Optional.of(candidateRepository.getReferenceById(candidateId))
                .orElseThrow(() -> {
                    log.error(CANDIDATE_NOT_FOUND + candidateId);
                    return new ResourceNotFoundException("Candidate not found with ID: " + candidateId);
                });
        if (technology != null && candidate != null) {
            log.debug("Technology--> " + technology);
            log.debug("Candidate--> " + candidate);

            technologyExpertiseService.deleteTechnologyExpertiseByCandidate(candidateId, technologyId);
        }
    }

}


