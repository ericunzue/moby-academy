package com.talento.moby.mappers;

import com.talento.moby.models.dto.CandidateDto;
import com.talento.moby.models.dto.CandidateWithTechnologiesDto;
import com.talento.moby.models.entities.Candidate;
import com.talento.moby.models.projections.TechnologyExpertiseProjection;

import java.util.List;

public class CandidateMapper {

    private CandidateMapper() {

    }

    public static CandidateDto mapToCandidateDto(Candidate candidate) {
        return CandidateDto.builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .surname(candidate.getSurname())
                .dni(candidate.getDni())
                .birthday(candidate.getBirthDate())
                //.technologies(candidate.getTechnologies())
                .build();
    }

    public static Candidate mapToCandidate(CandidateDto candidateDto) {
        return Candidate.builder()
                .id(candidateDto.getId())
                .name(candidateDto.getName())
                .surname(candidateDto.getSurname())
                .dni(candidateDto.getDni())
                .birthDate(candidateDto.getBirthday())
                //.technologies(candidateDto.getTechnologies())
                .build();
    }

    public static CandidateWithTechnologiesDto mapToCandidateWithTechnologiesDto(Candidate candidate, List<TechnologyExpertiseProjection> technologyExpertises) {
        return CandidateWithTechnologiesDto
                .builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .surname(candidate.getSurname())
                .technologies(technologyExpertises)
                .build();
    }
}
