package com.talento.moby.services;

import com.talento.moby.models.dto.CandidateDto;
import com.talento.moby.models.entities.Candidate;

import java.util.List;

public interface CandidateService {
    Candidate save(CandidateDto newCandidate);

    Candidate getOne(Long candidateId);

    Candidate update(Long candidateId, CandidateDto candidateInformation);

    Candidate delete(Long candidateId);

    List<Candidate> getAll();


}
