package com.talento.moby.services.impl;

import com.talento.moby.exception.BadRequestException;
import com.talento.moby.exception.NoContentException;
import com.talento.moby.exception.ResourceNotFoundException;
import com.talento.moby.models.dto.TechnologyDto;
import com.talento.moby.models.entities.Technology;
import com.talento.moby.repositories.TechnologyRepository;
import com.talento.moby.services.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.talento.moby.mappers.TechnologyMapper.mapToTechnology;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;

    @Override
    @Transactional
    public Technology save(TechnologyDto newTechnology) {
        return Optional.of(technologyRepository.save(mapToTechnology(newTechnology)))
                .orElseThrow(BadRequestException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Technology getById(Long technologyId) {
        try {
            return technologyRepository.findById(technologyId).get();
        } catch (Exception e) {
            throw new ResourceNotFoundException("Technology not found with technologyId " + technologyId);
        }
    }

    @Override
    @Transactional
    public Technology update(Long technologyId, TechnologyDto technologyInformation) {
        Technology technology = Optional.of(technologyRepository.findById(technologyId))
                .get()
                .orElseThrow(() -> new ResourceNotFoundException("Technology not found with technologyId " + technologyId));

        technology.setName(technologyInformation.getName());
        technology.setVersion(technologyInformation.getVersion());

        return technologyRepository.save(technology);
    }

    @Override
    @Transactional
    public Technology delete(Long technologyId) {
        return Optional.of(technologyRepository.findById(technologyId)).get()
                .orElseThrow(() -> new ResourceNotFoundException("Technology not found with technologyId " + technologyId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Technology> getAll() {
        List<Technology> technologies = technologyRepository.findAll();
        if (technologies.isEmpty()) {
            throw new NoContentException();
        }
        return technologies;
    }
}
