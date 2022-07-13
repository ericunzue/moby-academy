package com.talento.moby.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;

@Entity
@Table(name = "technologiesExpertise")
@IdClass(value = TechnologyExpertiseId.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class TechnologyExpertise {

    @Id
    @ManyToOne
    @JoinColumn(name = "technologyId", nullable = false)
    private Technology technology;

    @Id
    @ManyToOne
    @JoinColumn(name = "candidateId", nullable = false)
    private Candidate candidate;

    @Column(name = "expertiseInYears")
    @Min(value = 1, message = "Expertise cannot be 0")
    private int years;
}
