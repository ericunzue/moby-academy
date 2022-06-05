package com.talento.moby.services.impl;

import com.talento.moby.exception.BadRequestException;
import com.talento.moby.exception.NoContentException;
import com.talento.moby.exception.ResourceAlreadyExistsException;
import com.talento.moby.exception.ResourceNotFoundException;
import com.talento.moby.mappers.CandidateMapper;
import com.talento.moby.models.dto.CandidateDto;
import com.talento.moby.models.entities.Candidate;
import com.talento.moby.models.entities.Technology;
import com.talento.moby.repositories.CandidateRepository;
import com.talento.moby.repositories.TechnologyRepository;
import com.talento.moby.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private TechnologyRepository technologyRepository;

    @Override
    @Transactional
    public Candidate save(CandidateDto newCandidate) {
        return Optional.of(candidateRepository.save(CandidateMapper.candidateDtoToCandidate(newCandidate)))
                .orElseThrow(() -> new BadRequestException());
        
    }

    @Override
    @Transactional(readOnly = true)
    public Candidate getById(Long candidateId) {
        try {
            return candidateRepository.findById(candidateId).get();
        } catch (Exception e) {
            throw new ResourceNotFoundException("the resource does not exist");
        }
    }

    @Override
    @Transactional
    public Candidate update(Long candidateId, CandidateDto candidateInformation) {
        Candidate candidate = Optional.of(candidateRepository.findById(candidateId))
                .get()
                .orElseThrow(ResourceNotFoundException::new);

        candidate.setName(candidateInformation.getName());
        candidate.setSurname(candidateInformation.getSurname());
        candidate.setDni(candidateInformation.getDni());
        candidate.setTechnologies(candidateInformation.getTechnologies());

        return candidateRepository.save(candidate);
    }

    @Override
    @Transactional
    public Candidate delete(Long candidateId) {
        Optional<Candidate> candidate = candidateRepository.findById(candidateId);
        try {
            if (candidate.isPresent()) {
                candidateRepository.deleteById(candidateId);
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException("User not found with candidateId " + candidateId);
        }
        return candidate.get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Candidate> getAll() {
        List<Candidate> candidates = candidateRepository.findAll();

        if (candidates.isEmpty()) {
            throw new NoContentException();
        }
        return candidateRepository.findAll(Sort.by("surname"));
    }

    @Override
    public Candidate addTechnology(Long candidateId, Long techId) {
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow(BadRequestException::new);
        Technology technology = technologyRepository.findById(techId).orElseThrow(BadRequestException::new);
        if (!candidate.getTechnologies().contains(technology)) {
            candidate.addTechnology(technology);
            return candidateRepository.save(candidate);
        } else {
            throw new ResourceAlreadyExistsException("Candidate already has the technology " + technology);
        }

    }
}
