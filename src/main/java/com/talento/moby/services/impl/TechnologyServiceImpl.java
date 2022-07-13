package com.talento.moby.services.impl;

import com.talento.moby.exception.BadRequestException;
import com.talento.moby.exception.NoContentException;
import com.talento.moby.exception.ResourceNotFoundException;
import com.talento.moby.models.dto.TechnologyDto;
import com.talento.moby.models.dto.TechnologyWithCandidatesDto;
import com.talento.moby.models.entities.Technology;
import com.talento.moby.models.projections.CandidatesExpertiseProjection;
import com.talento.moby.repositories.TechnologyRepository;
import com.talento.moby.services.TechnologyService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.talento.moby.mappers.TechnologyMapper.mapToTechnology;

@Service
@Log
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
        return technologyRepository.findById(technologyId).orElseThrow(() -> new ResourceNotFoundException("Technology not found with technologyId " + technologyId));

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
    public void delete(Long technologyId) {
        var technology = Optional.of(technologyRepository.findById(technologyId)).get()
                .orElseThrow(() -> new ResourceNotFoundException("Technology not found with technologyId " + technologyId));
        technologyRepository.delete(technology);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Technology> getAll() {
        return Optional.of(technologyRepository.findAll()).orElse(null);
    }

    @Override
    public TechnologyWithCandidatesDto getCandidates(TechnologyDto technologyDto) {

        var technology = this.findByNameAndVersion(technologyDto);

        List<CandidatesExpertiseProjection> candidates;

        if (technology.isPresent()) {
            candidates = technologyRepository.getCandidates(technology.get().getId());
            return new TechnologyWithCandidatesDto(technology.get(), candidates);
        } else {
            throw new NoContentException();
        }

    }

    @Override
    public Optional<Technology> findByNameAndVersion(TechnologyDto technologyDto) {
        return Optional.ofNullable(technologyRepository.findTechnologyBy(technologyDto.getName(), technologyDto.getVersion())
                .orElseThrow(() -> new ResourceNotFoundException("Technology not found")));
    }


}
