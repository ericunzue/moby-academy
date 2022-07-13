package com.talento.moby.services.impl;

import com.talento.moby.models.entities.TechnologyExpertise;
import com.talento.moby.repositories.CandidateRepository;
import com.talento.moby.repositories.TechnologyExpertiseRepository;
import com.talento.moby.repositories.TechnologyRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static com.talento.moby.testUtils.TestEntityFactory.get_candidate;
import static com.talento.moby.testUtils.TestEntityFactory.get_projection_technologies_list;
import static com.talento.moby.testUtils.TestEntityFactory.get_technology;
import static com.talento.moby.testUtils.TestEntityFactory.get_technology_expertise;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TechnologyExpertiseServiceImplTest {

    @Mock
    private TechnologyRepository technologyRepository;

    @Mock
    private TechnologyExpertiseRepository technologyExpertiseRepository;

    @Mock
    private CandidateRepository candidateRepository;

    @InjectMocks
    private TechnologyExpertiseServiceImpl technologyExpertiseService;


    @Test
    void save() {
        int expertise = 1;
        when(technologyRepository.getReferenceById(1L)).thenReturn(get_technology());
        when(candidateRepository.getReferenceById(1L)).thenReturn(get_candidate());
        when(technologyExpertiseRepository.save(get_technology_expertise())).thenReturn(get_technology_expertise());
        var technology = technologyRepository.getReferenceById(1L);
        var candidate = candidateRepository.getReferenceById(1L);
        var actual = technologyExpertiseRepository.save(get_technology_expertise());
        var technologyExpertise = new TechnologyExpertise();
        technologyExpertise.setCandidate(candidate);
        technologyExpertise.setTechnology(technology);
        technologyExpertise.setYears(expertise);
        technologyExpertiseService.save(1L, 1L, 3);
        assertEquals(actual, technologyExpertise);

    }


    @Test
    void getTechnologiesAndYearsOfExperienceByCandidate() {

        when(technologyExpertiseRepository.getTechnologiesAndYearsOfExperienceByCandidate(1L)).thenReturn(get_projection_technologies_list());
        var technologies = technologyExpertiseRepository.getTechnologiesAndYearsOfExperienceByCandidate(1L);
        assertEquals(technologies.size(), get_projection_technologies_list().size());
        verify(technologyExpertiseRepository, atLeastOnce()).getTechnologiesAndYearsOfExperienceByCandidate(1L);
        technologyExpertiseService.getTechnologiesAndYearsOfExperienceByCandidate(1L);

    }

    @Test
    void deleteTechnologyExpertiseByCandidate() {
        technologyExpertiseRepository.deleteTechnologyExpertiseByCandidate(1L, 1L);
        verify(technologyExpertiseRepository, times(1)).deleteTechnologyExpertiseByCandidate(1L, 1L);
        technologyExpertiseService.deleteTechnologyExpertiseByCandidate(1L, 1L);
    }
}