package com.talento.moby.models.entities;

import com.talento.moby.models.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

@Entity(name = "dni")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    @Id
    @Column(name = "dniId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name = "dniNumber", unique = true, nullable = false)
    @NotEmpty(message = "number cannot be empty")
    private Integer number;

    @OneToOne(mappedBy = "dni")
    private Candidate candidate;

    @Enumerated(EnumType.STRING)
    @Column(name = "dniType", nullable = false)
    @NotEmpty(message = "type cannot be empty")
    private Type type;
}
