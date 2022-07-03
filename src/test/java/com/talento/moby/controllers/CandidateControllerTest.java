package com.talento.moby.controllers;

import com.talento.moby.services.impl.CandidateServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.talento.moby.testUtils.TestEntityFactory.get_candidates;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CandidateControllerTest {
    @MockBean
    CandidateServiceImpl candidateService;

    @MockBean
    private MockMvc mvc;

    @Test
    void get_all_candidates_ok_test() throws Exception {

        when(candidateService.getAll()).thenReturn(get_candidates());
        mvc.perform(get("api/candidates")).andExpect(status().isOk());
    }

    @Test
    void getById() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void addTechnology() {
    }

    @Test
    void getTechnologies() {
    }

    @Test
    void deleteCandidateExpertise() {
    }
}