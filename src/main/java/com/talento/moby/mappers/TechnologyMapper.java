package com.talento.moby.mappers;

import com.talento.moby.models.dto.TechnologyDto;
import com.talento.moby.models.entities.Technology;

public class TechnologyMapper {
    private TechnologyMapper() {

    }

    public static TechnologyDto technologyToDto(Technology technology) {
        return TechnologyDto.builder()
                .id(technology.getId())
                .name(technology.getName())
                .version(technology.getVersion())
                .build();
    }

    public static Technology technologyDtoToTechnologyWithoutId(TechnologyDto technology) {
        return Technology.builder()
                .name(technology.getName())
                .version(technology.getVersion())
                .build();
    }

    public static Technology technologyDtoToTechnology(TechnologyDto technologyDto) {
        return Technology.builder()
                .id(technologyDto.getId())
                .name(technologyDto.getName())
                .version(technologyDto.getVersion())
                .build();
    }
}
