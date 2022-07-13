package com.talento.moby.services.impl;

import com.talento.moby.exception.ResourceNotFoundException;
import com.talento.moby.models.entities.Candidate;
import com.talento.moby.repositories.CandidateRepository;
import com.talento.moby.repositories.TechnologyRepository;
import com.talento.moby.services.TechnologyExpertiseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static com.talento.moby.mappers.CandidateMapper.mapToCandidateWithTechnologiesDto;
import static com.talento.moby.testUtils.TestEntityFactory.get_candidate;
import static com.talento.moby.testUtils.TestEntityFactory.get_candidate_dto;
import static com.talento.moby.testUtils.TestEntityFactory.get_candidate_without_id;
import static com.talento.moby.testUtils.TestEntityFactory.get_candidates;
import static com.talento.moby.testUtils.TestEntityFactory.get_projection_technologies_list;
import static com.talento.moby.testUtils.TestEntityFactory.get_technology;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CandidateServiceImplTest {


    @Mock
    private CandidateRepository candidateRepository;


    @Mock
    private TechnologyRepository technologyRepository;

    @Mock
    private TechnologyExpertiseService technologyExpertiseService;

    @InjectMocks
    private CandidateServiceImpl candidateService;

    @Test
    void save() {

        when(candidateRepository.save(get_candidate_without_id())).thenReturn(get_candidate());
        var candidate = get_candidate();
        var candidate2 = candidateService.save(get_candidate_dto());

        assertEquals(candidate, candidate2);
    }

    @Test
    void getById() {

        when(candidateRepository.findById(1L)).thenReturn(Optional.ofNullable(get_candidate()));
        var actual = candidateService.getById(1L);
        assertEquals(get_candidate(), actual);
    }

    @Test
    void update() {
        when(candidateRepository.findById(1L)).thenReturn(Optional.ofNullable(get_candidate()));

        var candidate = candidateRepository.findById(1L).get();
        var candidateDto = get_candidate_dto();

        candidate.setName(candidateDto.getName());
        candidate.setSurname(candidateDto.getSurname());
        candidate.setBirthDate(candidateDto.getBirthDate());
        candidate.setDni(candidateDto.getDni());


        when(candidateRepository.save(candidate)).thenReturn(candidate);
        when(candidateService.update(1L, candidateDto)).thenReturn(candidate);
        var actual = candidateRepository.save(candidate);
        var expected = candidateService.update(1L, candidateDto);

        assertEquals(actual, expected);


    }

    @Test
    void delete() {
        when(candidateRepository.findById(1L)).thenReturn(Optional.ofNullable(get_candidate()));
        Optional<Candidate> candidate = candidateRepository.findById(1L);
        assertNotNull(candidate);
        candidateService.delete(1L);
        verify(candidateRepository, atLeastOnce()).deleteById(1L);
    }


    @Test
    void getAll() {
        when(candidateRepository.findAll()).thenReturn(get_candidates());
        candidateService.getAll();

    }

    @Test
    void getTechnologies() {
        when(candidateRepository.findById(1L)).thenReturn(Optional.ofNullable(get_candidate()));
        when(technologyExpertiseService.getTechnologiesAndYearsOfExperienceByCandidate(1L)).thenReturn(get_projection_technologies_list());
        Optional<Candidate> candidateOptional = candidateRepository.findById(1L);
        var candidate = candidateOptional.get();
        var technologies = technologyExpertiseService.getTechnologiesAndYearsOfExperienceByCandidate(1L);
        var expected = mapToCandidateWithTechnologiesDto(candidate, technologies);
        assertNotNull(candidate);
        assertNotNull(technologies);
        assertNotNull(expected);
        candidateService.getTechnologies(1L);
        verify(candidateRepository, atLeastOnce()).findById(1L);
        verify(technologyExpertiseService, atLeastOnce()).getTechnologiesAndYearsOfExperienceByCandidate(1L);
    }

    @Test
    void get_technologies_throw_exception() {
        Long candidateId = 1L;
        when(candidateRepository.findById(candidateId)).thenThrow(new ResourceNotFoundException("Candidate not found with Id: " + candidateId));

        assertThrows(ResourceNotFoundException.class, () -> candidateService.getTechnologies(candidateId));
        verify(candidateRepository, times(1)).findById(candidateId);

    }

    @Test
    void deleteExpertise() {
        when(technologyRepository.getReferenceById(1L)).thenReturn(get_technology());
        when(candidateRepository.getReferenceById(1L)).thenReturn(get_candidate());
        var technology = technologyRepository.getReferenceById(1L);
        var candidate = candidateRepository.getReferenceById(1L);
        assertNotNull(technology);
        assertNotNull(candidate);
        verify(technologyRepository, times(1)).getReferenceById(1L);
        verify(candidateRepository, times(1)).getReferenceById(1L);
        candidateService.deleteExpertise(1L, 1L);

    }
}