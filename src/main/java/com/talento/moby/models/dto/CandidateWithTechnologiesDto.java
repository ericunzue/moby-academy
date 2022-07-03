package com.talento.moby.models.dto;

import com.talento.moby.models.projections.TechnologyExpertiseProjection;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
public class CandidateWithTechnologiesDto {
    private Long id;

    @NotBlank()
    @Size(min = 1, message = "name cannot be empty")
    private String name;

    @NotBlank()
    @Size(min = 1, message = "name cannot be empty")
    private String surname;

    private List<TechnologyExpertiseProjection> technologies;
}
