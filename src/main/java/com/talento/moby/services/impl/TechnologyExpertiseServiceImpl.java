package com.talento.moby.services.impl;

import com.talento.moby.exception.ResourceNotFoundException;
import com.talento.moby.models.entities.TechnologyExpertise;
import com.talento.moby.models.projections.TechnologyExpertiseProjection;
import com.talento.moby.repositories.CandidateRepository;
import com.talento.moby.repositories.TechnologyExpertiseRepository;
import com.talento.moby.repositories.TechnologyRepository;
import com.talento.moby.services.TechnologyExpertiseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TechnologyExpertiseServiceImpl implements TechnologyExpertiseService {
    @Autowired
    private TechnologyExpertiseRepository technologyExpertiseRepository;

    @Autowired
    private TechnologyRepository technologyRepository;

    @Autowired
    private CandidateRepository candidateRepository;


    @Override
    public void save(Long candidateId, Long technologyId, int expertise) {
        var technology = Optional.of(technologyRepository.getReferenceById(technologyId))
                .orElseThrow(() -> {
                    log.error("Get technology on save method in TechnologyExpertiseServiceImpl--> " + new ResourceNotFoundException());
                    return new ResourceNotFoundException("Technology not found with ID: " + technologyId);
                });

        var candidate = Optional.of(candidateRepository.getReferenceById(candidateId))
                .orElseThrow(() -> {
                    log.error("Get candidate on save method in TechnologyExpertiseServiceImpl--> " + new ResourceNotFoundException());
                    return new ResourceNotFoundException("Technology not found with ID: " + candidateId);
                });

        var technologyExpertise = new TechnologyExpertise();
        technologyExpertise.setCandidate(candidate);
        technologyExpertise.setTechnology(technology);
        technologyExpertise.setYears(expertise);
        log.debug("technologyExpertise on save method in TechnologyExpertiseServiceImpl--> " + technologyExpertise);


        technologyExpertiseRepository.save(technologyExpertise);


    }

    @Override
    public List<TechnologyExpertiseProjection> getTechnologiesAndYearsOfExperienceByCandidate(Long candidateId) {
        return Optional.of(technologyExpertiseRepository.getTechnologiesAndYearsOfExperienceByCandidate(candidateId)).orElse(null);
    }

    @Override
    @Transactional
    public void deleteTechnologyExpertiseByCandidate(Long candidateId, Long technologyId) {
        technologyExpertiseRepository.deleteTechnologyExpertiseByCandidate(candidateId, technologyId);
    }
}
