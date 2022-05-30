package com.talento.moby.models.dto;

import com.talento.moby.models.entities.Technology;

public class TechnologyMapper {

    public static TechnologyDto technologyToDto(Technology technology) {
        return TechnologyDto.builder()
                .id(technology.getId())
                .name(technology.getName())
                .version(technology.getVersion())
                .build();
    }
}
