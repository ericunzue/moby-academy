package com.talento.moby.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TechnologyExpertiseDto {

    @NotEmpty(message = "Name cannot be blank")
    private Long technologyId;

    @NotEmpty(message = "Name cannot be blank")
    private String name;

    @NotEmpty(message = "Name cannot be blank")
    private int version;

    @NotEmpty(message = "Name cannot be blank")
    private int expertise;
}
