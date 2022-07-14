package com.talento.moby.services.impl;

import com.talento.moby.exception.ResourceNotFoundException;
import com.talento.moby.repositories.TechnologyRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static com.talento.moby.testUtils.TestEntityFactory.get_candidates_expertise_projection_list;
import static com.talento.moby.testUtils.TestEntityFactory.get_technologies;
import static com.talento.moby.testUtils.TestEntityFactory.get_technology;
import static com.talento.moby.testUtils.TestEntityFactory.get_technology_dto;
import static com.talento.moby.testUtils.TestEntityFactory.get_technology_without_id;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TechnologyServiceImplTest {

    @Mock
    private TechnologyRepository technologyRepository;

    @InjectMocks
    private TechnologyServiceImpl technologyService;

    @Test
    void save() {
        when(technologyRepository.save(get_technology_without_id())).thenReturn(get_technology());
        var expected = get_technology();
        var actual = technologyService.save(get_technology_dto());

        assertEquals(expected, actual);
    }

    @Test
    void getById() {

        when(technologyRepository.findById(1L)).thenReturn(Optional.ofNullable(get_technology()));
        var actual = technologyService.getById(1L);
        assertEquals(get_technology(), actual);
    }

    @Test
    void update() {
        when(technologyRepository.findById(1L)).thenReturn(Optional.ofNullable(get_technology()));

        var technology = technologyRepository.findById(1L).get();
        var technologyDto = get_technology_dto();

        technology.setName(technologyDto.getName());
        technology.setVersion(technologyDto.getVersion());

        when(technologyRepository.save(technology)).thenReturn(technology);
        when(technologyService.update(1L, technologyDto)).thenReturn(technology);
        var actual = technologyRepository.save(technology);
        var expected = technologyService.update(1L, technologyDto);

        assertEquals(actual, expected);
    }

    @Test
    void delete() {
        when(technologyRepository.findById(1L)).thenReturn(Optional.ofNullable(get_technology()));
        var technology = technologyRepository.findById(1L);
        assertNotNull(technology);
        technologyService.delete(1L);
        technologyRepository.delete(technology.get());
        verify(technologyRepository, atLeastOnce()).delete(technology.get());

    }

    @Test
    void delete_throw_exception() {
        when(technologyRepository.findById(1L)).thenThrow(new ResourceNotFoundException());
        assertThrows(ResourceNotFoundException.class, () -> technologyService.delete(1L));
        verify(technologyRepository, times(1)).findById(1L);
    }

    @Test
    void getAll() {
        when(technologyRepository.findAll()).thenReturn(get_technologies());
        var technologiesList = technologyService.getAll();
        assertNotNull(technologiesList);
    }

    @Test
    void getCandidates() {
        when(technologyRepository.findTechnologyBy("Java", 8)).thenReturn(Optional.ofNullable(get_technology()));
        when(technologyService.findByNameAndVersion(get_technology_dto())).thenReturn(Optional.ofNullable(get_technology()));
        var technology = technologyService.findByNameAndVersion(get_technology_dto());
        when(technologyRepository.getCandidates(1L)).thenReturn(get_candidates_expertise_projection_list());

        var candidates = technologyRepository.getCandidates(1L);
        assertNotNull(candidates);
        technologyService.getCandidates(get_technology_dto());

    }

    @Test
    void findByNameAndVersion() {
        var technologyDto = get_technology_dto();
        when(technologyRepository.findTechnologyBy(technologyDto.getName(), technologyDto.getVersion())).thenReturn(Optional.ofNullable(get_technology()));
        var technology = technologyRepository.findTechnologyBy(technologyDto.getName(), technologyDto.getVersion());
        technologyService.findByNameAndVersion(technologyDto);
        assertNotNull(technology);
    }
}