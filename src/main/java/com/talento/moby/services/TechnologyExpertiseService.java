package com.talento.moby.services;

import com.talento.moby.models.entities.TechnologyExpertise;
import com.talento.moby.models.projections.TechnologyExpertiseProjection;

import java.util.List;

public interface TechnologyExpertiseService {
    void save(Long candidateId, Long technologyId, int expertise);

    TechnologyExpertise getById(Long technologyId);

    TechnologyExpertise update(Long technologyId, TechnologyExpertise technologyInformation);

    TechnologyExpertise delete(Long technologyId);

    List<TechnologyExpertise> getAll();

    List<TechnologyExpertiseProjection> getTechnologiesAndYearsOfExperienceByCandidate(Long candidateId);

}

