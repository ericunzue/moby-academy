package com.talento.moby.models.dto;

import com.talento.moby.models.entities.Technology;
import com.talento.moby.models.projections.CandidatesExpertiseProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TechnologyWithCandidatesDto {

    private Technology technology;

    private List<CandidatesExpertiseProjection> candidates;
}
