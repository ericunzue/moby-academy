package com.talento.moby.services.impl;

import com.talento.moby.exception.BadRequestException;
import com.talento.moby.exception.ResourceNotFoundException;
import com.talento.moby.mappers.TechnologyMapper;
import com.talento.moby.models.dto.TechnologyDto;
import com.talento.moby.models.entities.Technology;
import com.talento.moby.repositories.TechnologyRepository;
import com.talento.moby.services.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;

    @Override
    @Transactional
    public Technology save(TechnologyDto newTechnology) {
        return Optional.of(technologyRepository.save(TechnologyMapper.technologyDtoToTechnology(newTechnology)))
                .orElseThrow(BadRequestException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Technology getById(Long technologyId) {
        try {
            return technologyRepository.findById(technologyId).get();
        } catch (Exception e) {
            throw new ResourceNotFoundException("the resource does not exist");
        }
    }

    @Override
    @Transactional
    public Technology update(Long technologyId, TechnologyDto technologyInformation) {
        Technology technology = Optional.of(technologyRepository.findById(technologyId))
                .get()
                .orElseThrow(ResourceNotFoundException::new);

        technology.setName(technologyInformation.getName());
        technology.setVersion(technologyInformation.getVersion());

        return technologyRepository.save(technology);
    }

    @Override
    @Transactional
    public Technology delete(Long technologyId) {
        Optional<Technology> technology = technologyRepository.findById(technologyId);
        try {
            if (technology.isPresent()) {
                technologyRepository.deleteById(technologyId);
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException("the resource does not exist");
        }
        return technology.get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Technology> getAll() {
        return technologyRepository.findAll();
    }
}
