package com.talento.moby.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "candidates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "candidateId")
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotEmpty(message = "name cannot be empty")
    private String name;

    @Column(name = "surName", nullable = false)
    @NotEmpty(message = "surname cannot be empty")
    private String surName;

    @Column(name = "birthDate", nullable = false)
    @NotEmpty(message = "birth date cannot be empty")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotEmpty(message = "dni cannot be empty")
    @OneToOne()
    @JoinColumn(name = "dniId", referencedColumnName = "dniId", nullable = false)
    private Document dni;

    @NotEmpty
    @OneToMany()
    @Column(name = "technologyId")
    List<Technology> technologies;


}
