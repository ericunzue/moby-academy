package com.talento.moby.mappers;

import com.talento.moby.models.dto.CandidateDto;
import com.talento.moby.models.entities.Candidate;

public class CandidateMapper {

    private CandidateMapper() {

    }

    public static CandidateDto mapToCandidateDto(Candidate candidate) {
        return CandidateDto.builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .surname(candidate.getSurname())
                .dni(candidate.getDni())
                .birthday(candidate.getBirthday())
                .technologies(candidate.getTechnologies())
                .build();
    }

    public static Candidate mapToCandidate(CandidateDto candidateDto) {
        return Candidate.builder()
                .id(candidateDto.getId())
                .name(candidateDto.getName())
                .surname(candidateDto.getSurname())
                .dni(candidateDto.getDni())
                .birthday(candidateDto.getBirthday())
                .technologies(candidateDto.getTechnologies())
                .build();
    }
}
