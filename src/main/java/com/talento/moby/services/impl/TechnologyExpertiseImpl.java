package com.talento.moby.services.impl;

import com.talento.moby.models.entities.TechnologyExpertise;
import com.talento.moby.models.projections.TechnologyExpertiseProjection;
import com.talento.moby.repositories.CandidateRepository;
import com.talento.moby.repositories.TechnologyExpertiseRepository;
import com.talento.moby.repositories.TechnologyRepository;
import com.talento.moby.services.TechnologyExpertiseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TechnologyExpertiseImpl implements TechnologyExpertiseService {
    @Autowired
    private TechnologyExpertiseRepository technologyExpertiseRepository;

    @Autowired
    private TechnologyRepository technologyRepository;

    @Autowired
    private CandidateRepository candidateRepository;


    @Override
    public void save(Long candidateId, Long technologyId, int expertise) {
        var technology = Optional.of(technologyRepository.getReferenceById(technologyId))
                .orElseThrow(() -> new EntityNotFoundException("Technology not found with ID: " + technologyId));

        var candidate = Optional.of(candidateRepository.getReferenceById(candidateId))
                .orElseThrow(() -> new EntityNotFoundException("Technology not found with ID: " + candidateId));

        var technologyExpertise = new TechnologyExpertise();
        technologyExpertise.setCandidate(candidate);
        technologyExpertise.setTechnology(technology);
        technologyExpertise.setYears(expertise);


        candidateRepository.save(candidate);


    }

    @Override
    public TechnologyExpertise getById(Long technologyId) {
        return null;
    }

    @Override
    public TechnologyExpertise update(Long technologyId, TechnologyExpertise technologyInformation) {
        return null;
    }

    @Override
    public TechnologyExpertise delete(Long technologyId) {
        return null;
    }

    @Override
    public List<TechnologyExpertise> getAll() {
        return null;
    }

    @Override
    public List<TechnologyExpertiseProjection> getTechnologiesAndYearsOfExperienceByCandidate(Long candidateId) {

        return Optional.of(technologyExpertiseRepository.getTechnologiesAndYearsOfExperienceByCandidate(candidateId)).orElse(null);

    }
}
