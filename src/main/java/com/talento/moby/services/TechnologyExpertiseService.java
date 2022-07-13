package com.talento.moby.services;

import com.talento.moby.models.projections.TechnologyExpertiseProjection;

import java.util.List;


public interface TechnologyExpertiseService {
    void save(Long candidateId, Long technologyId, int expertise);

    List<TechnologyExpertiseProjection> getTechnologiesAndYearsOfExperienceByCandidate(Long candidateId);

    void deleteTechnologyExpertiseByCandidate(Long candidateId, Long technologyId);

}

