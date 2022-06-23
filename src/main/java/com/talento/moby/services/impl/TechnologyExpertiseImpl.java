package com.talento.moby.services.impl;

import com.talento.moby.models.entities.TechnologyExpertise;
import com.talento.moby.repositories.TechnologyExpertiseRepository;
import com.talento.moby.services.TechnologyExpertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyExpertiseImpl implements TechnologyExpertiseService {
    @Autowired
    private TechnologyExpertiseRepository technologyExpertiseRepository;

    @Override
    public TechnologyExpertise save(TechnologyExpertise expertise) {
        return null;
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
        return technologyExpertiseRepository.findAll();
    }
}
