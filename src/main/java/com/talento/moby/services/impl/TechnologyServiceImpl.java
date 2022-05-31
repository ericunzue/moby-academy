package com.talento.moby.services.impl;

import com.talento.moby.models.dto.TechnologyDto;
import com.talento.moby.models.entities.Technology;
import com.talento.moby.repositories.TechnologyRepository;
import com.talento.moby.services.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;

    @Override
    public Technology save(TechnologyDto newTechnology) {
        return null;
    }

    @Override
    public Technology getOne(Long technologyId) {
        return null;
    }

    @Override
    public Technology update(Long technologyId, TechnologyDto technologyInformation) {
        return null;
    }

    @Override
    public Technology delete(Long technologyId) {
        return null;
    }

    @Override
    public List<Technology> getAll() {
        return null;
    }
}
