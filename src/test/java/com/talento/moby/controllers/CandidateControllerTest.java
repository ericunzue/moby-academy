package com.talento.moby.controllers;

import com.google.gson.Gson;
import com.talento.moby.models.entities.Candidate;
import com.talento.moby.services.CandidateService;
import com.talento.moby.services.TechnologyExpertiseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.talento.moby.testUtils.TestEntityFactory.get_candidate;
import static com.talento.moby.testUtils.TestEntityFactory.get_candidate_dto;
import static com.talento.moby.testUtils.TestEntityFactory.get_candidate_with_technologies;
import static com.talento.moby.testUtils.TestEntityFactory.get_candidates;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CandidateController.class)
class CandidateControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CandidateService candidateService;

    @MockBean
    private TechnologyExpertiseService technologyExpertiseService;

    @Test
    void get_all_candidates_ok_test() throws Exception {
        List<Candidate> candidates = get_candidates();
        when(candidateService.getAll()).thenReturn(candidates);
        mockMvc.perform(get("/api/candidates")).andExpect(status().isOk());
    }

    @Test
    void get_by_id_test() throws Exception {

        when(candidateService.getById(1L)).thenReturn(get_candidate());
        mockMvc.perform(get("/api/candidates/{id}", 1)).andExpect(status().isOk());
    }

    @Test
    void save_test() throws Exception {
        var candidateDto = get_candidate_dto();


        when(candidateService.save(candidateDto)).thenReturn(get_candidate());
        String jsonCandidate = new Gson().toJson(candidateDto);
        mockMvc.perform(post("/api/candidates/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCandidate))
                .andExpect(status().isCreated());

    }

    @Test
    void update_test() throws Exception {
        Long candidateId = 1L;
        var candidateDto = get_candidate_dto();


        when(candidateService.update(candidateId, candidateDto)).thenReturn(get_candidate());
        String jsonCandidate = new Gson().toJson(candidateDto);
        String jsoCandidate = new Gson().toJson(get_candidate());
        mockMvc.perform(put("/api/candidates/{candidateId}", candidateId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCandidate))
                .andExpect(status().isOk())
                .andExpect(content().json(jsoCandidate));
    }

    @Test
    void delete() throws Exception {
        Long candidateId = 1L;

        candidateService.delete(candidateId);
        verify(candidateService, times(1)).delete(candidateId);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/candidates/delete/{id}", candidateId))
                .andExpect(status().isOk());
    }

    @Test
    void addTechnology() throws Exception {
        Long candidateId = 1L;
        Long technologyId = 1L;
        int expertise = 3;
        technologyExpertiseService.save(candidateId, technologyId, expertise);
        verify(technologyExpertiseService, times(1)).save(candidateId, technologyId, expertise);
        mockMvc.perform(put("/api/candidates/add/technology/{candidateId}/{technologyId}", candidateId, technologyId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(expertise)))
                .andExpect(status().isOk());
    }

    @Test
    void getTechnologies() throws Exception {
        candidateService.getTechnologies(1L);
        when(candidateService.getTechnologies(1L)).thenReturn(get_candidate_with_technologies());
        verify(candidateService, times(1)).getTechnologies(1L);
        mockMvc.perform(get("/api/candidates/technologies/{candidateId}", 1L))
                .andExpect(status().isOk());

    }

    @Test
    void deleteCandidateExpertise() throws Exception {

        candidateService.deleteExpertise(1L, 1L);
        verify(candidateService, times(1)).deleteExpertise(1L, 1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/candidates/expertise/delete/{candidateId}/{technologyId}", 1L, 1L))
                .andExpect(result -> status().isOk());
    }
}