package com.talento.moby.services.impl;

import com.talento.moby.exception.BadRequestException;
import com.talento.moby.exception.ResourceNotFoundException;
import com.talento.moby.mappers.CandidateMapper;
import com.talento.moby.models.dto.CandidateDto;
import com.talento.moby.models.entities.Candidate;
import com.talento.moby.repositories.CandidateRepository;
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

    @Override
    @Transactional
    public Candidate save(CandidateDto newCandidate) {

        return Optional.of(candidateRepository.save(CandidateMapper.candidateDtoToCandiate(newCandidate)))
                .orElseThrow(BadRequestException::new);

    }

    @Override
    @Transactional(readOnly = true)
    public Candidate getOne(Long candidateId) {
        return Optional.of(candidateRepository.findById(candidateId))
                .get()
                .orElseThrow(ResourceNotFoundException::new);

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

        return candidateRepository.save(candidae);
    }

    @Override
    @Transactional
    public Candidate delete(Long candidateId) {
        Optional<Candidate> candidate = candidateRepository.findById(candidateId);
        if (candidate.isPresent()) {
            candidateRepository.delete(candidate.get());
            return candidate.get();
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Candidate> getAll() {
        return candidateRepository.findAll(Sort.by("surname"));
    }
}
