package com.talento.moby.services;

import com.talento.moby.models.dto.CandidateDto;
import com.talento.moby.models.dto.CandidateWithTechnologiesDto;
import com.talento.moby.models.entities.Candidate;

import java.util.List;

public interface CandidateService {
    Candidate save(CandidateDto newCandidate);

    Candidate getById(Long candidateId);

    Candidate update(Long candidateId, CandidateDto candidateInformation);

    void delete(Long candidateId);

    List<Candidate> getAll();

    Candidate addTechnology(Long candidateId, Long technologyId);

    CandidateWithTechnologiesDto getTechnologies(Long candidateId);
}
