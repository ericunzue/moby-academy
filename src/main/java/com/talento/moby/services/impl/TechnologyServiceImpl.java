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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.talento.moby.mappers.TechnologyMapper.mapToTechnology;

@Service
@Slf4j
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;

    private static final String TECHNOLOGY_NOT_FOUND = "Technology not found with technologyId ";

    @Override
    @Transactional
    public Technology save(TechnologyDto newTechnology) {
        log.debug("Technology to save-->" + newTechnology);
        return Optional.of(technologyRepository.save(mapToTechnology(newTechnology)))
                .orElseThrow(() -> {
                    log.error("technology not save");
                    return new BadRequestException();
                });
    }

    @Override
    @Transactional(readOnly = true)
    public Technology getById(Long technologyId) {
        return technologyRepository.findById(technologyId).orElseThrow(() -> {
            log.error(TECHNOLOGY_NOT_FOUND + technologyId);
            return new ResourceNotFoundException(TECHNOLOGY_NOT_FOUND + technologyId);
        });


    }

    @Override
    @Transactional
    public Technology update(Long technologyId, TechnologyDto technologyInformation) {
        Technology technology = Optional.of(technologyRepository.findById(technologyId))
                .get()
                .orElseThrow(() -> {
                    log.error(TECHNOLOGY_NOT_FOUND + technologyId);
                    return new ResourceNotFoundException(TECHNOLOGY_NOT_FOUND + technologyId);
                });

        technology.setName(technologyInformation.getName());
        log.debug(technologyInformation.getName());
        technology.setVersion(technologyInformation.getVersion());
        log.debug(String.valueOf(technologyInformation.getVersion()));

        return technologyRepository.save(technology);
    }

    @Override
    @Transactional
    public void delete(Long technologyId) {
        Optional.of(technologyRepository.findById(technologyId)).ifPresentOrElse(
                t -> technologyRepository.delete(t.get()),
                () -> {
                    throw new ResourceNotFoundException();
                }
        );

    }

    @Override
    @Transactional(readOnly = true)
    public List<Technology> getAll() {
        return Optional.of(technologyRepository.findAll()).orElse(null);
    }

    @Override
    public TechnologyWithCandidatesDto getCandidates(TechnologyDto technologyDto) {
        log.debug("TechnologyDto in GetCandidates--> " + technologyDto);
        var technology = this.findByNameAndVersion(technologyDto);
        log.debug("Technology in GetCandidates--> " + technology);
        List<CandidatesExpertiseProjection> candidates;

        if (technology.isPresent()) {
            candidates = technologyRepository.getCandidates(technology.get().getId());
            log.debug("Candidates in GetCandidates--> " + candidates);
            return new TechnologyWithCandidatesDto(technology.get(), candidates);
        } else {
            log.error("Candidates in GetCandidates--> " + new NoContentException());
            throw new NoContentException();
        }

    }

    @Override
    public Optional<Technology> findByNameAndVersion(TechnologyDto technologyDto) {
        log.debug("TechnologyDto in findByNameAndVersion --> " + technologyDto);
        return Optional.ofNullable(technologyRepository.findTechnologyBy(technologyDto.getName(), technologyDto.getVersion())
                .orElseThrow(() -> {
                    log.error("findByNameAndVersion-->" + new ResourceNotFoundException());
                    return new ResourceNotFoundException("Technology not found");
                }));
    }


}
