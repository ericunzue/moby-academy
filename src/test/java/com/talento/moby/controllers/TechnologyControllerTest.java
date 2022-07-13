package com.talento.moby.controllers;

import com.google.gson.Gson;
import com.talento.moby.models.entities.Technology;
import com.talento.moby.services.TechnologyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.talento.moby.testUtils.TestEntityFactory.get_technologies;
import static com.talento.moby.testUtils.TestEntityFactory.get_technologies_with_candidates;
import static com.talento.moby.testUtils.TestEntityFactory.get_technology;
import static com.talento.moby.testUtils.TestEntityFactory.get_technology_dto;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TechnologyController.class)
class TechnologyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TechnologyService technologyService;

    @Test
    void getAll() throws Exception {
        List<Technology> technologies = get_technologies();
        when(technologyService.getAll()).thenReturn(technologies);
        mockMvc.perform(get("/api/technologies")).andExpect(status().isOk());
    }

    @Test
    void getById_test() throws Exception {
        when(technologyService.getById(1L)).thenReturn(get_technology());
        mockMvc.perform(get("/api/technologies/{id}", 1)).andExpect(status().isAccepted());
    }

    @Test
    void save_test() throws Exception {
        var technologyDto = get_technology_dto();

        when(technologyService.save(technologyDto)).thenReturn(get_technology());
        String jsonTechnology = new Gson().toJson(technologyDto);
        mockMvc.perform(post("/api/technologies/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonTechnology))
                .andExpect(status().isCreated());
    }

    @Test
    void update_test() throws Exception {
        Long technologyId = 1L;
        var technologyDto = get_technology_dto();


        when(technologyService.update(technologyId, technologyDto)).thenReturn(get_technology());
        String actual = new Gson().toJson(technologyDto);
        String expected = new Gson().toJson(get_technology());
        mockMvc.perform(put("/api/technologies/{technologyId}", technologyId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(actual))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));

    }

    @Test
    void delete_test() throws Exception {
        Long technologyId = 1L;

        technologyService.delete(technologyId);
        verify(technologyService, times(1)).delete(technologyId);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/technologies/delete/{id}", technologyId))
                .andExpect(status().isOk());

    }


    @Test
    void getCandidatesByTechnology() throws Exception {

        when(technologyService.getCandidates(get_technology_dto())).thenReturn(get_technologies_with_candidates());
        String actual = new Gson().toJson(get_technology());

        mockMvc.perform(get("/api/technologies/candidates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(actual))
                .andExpect(status().isOk());

    }
}