package com.talento.moby.services;

import com.talento.moby.models.dto.TechnologyDto;
import com.talento.moby.models.dto.TechnologyWithCandidatesDto;
import com.talento.moby.models.entities.Technology;

import java.util.List;
import java.util.Optional;

public interface TechnologyService {
    Technology save(TechnologyDto newTechnology);

    Technology getById(Long technologyId);

    Technology update(Long technologyId, TechnologyDto technologyInformation);

    Technology delete(Long technologyId);

    List<Technology> getAll();

    Optional<Technology> findByNameAndVersion(TechnologyDto technologyDto);

    TechnologyWithCandidatesDto getCandidates(TechnologyDto technologyDto);


}
