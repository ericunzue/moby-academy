package com.talento.moby.services;

import com.talento.moby.models.entities.TechnologyExpertise;

import java.util.List;

public interface TechnologyExpertiseService {
    TechnologyExpertise save(TechnologyExpertise newTechnology);

    TechnologyExpertise getOne(Long technologyId);

    TechnologyExpertise update(Long technologyId, TechnologyExpertise technologyInformation);

    TechnologyExpertise delete(Long technologyId);

    List<TechnologyExpertise> getAll();
}

