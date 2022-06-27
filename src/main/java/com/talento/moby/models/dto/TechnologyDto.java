package com.talento.moby.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Valid
public class TechnologyDto {
    
    private Long id;

    @NotEmpty(message = "Name cannot be blank")
    private String name;

    @Min(value = 1)
    private int version;
}
