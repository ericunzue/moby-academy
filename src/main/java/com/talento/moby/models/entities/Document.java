package com.talento.moby.models.entities;

import com.talento.moby.models.enums.DocumentType;
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
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dni")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    @Id
    @Column(name = "dniId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "dniNumber", unique = true, nullable = false)
    @NotNull()
    @Min(value = 8, message = "number must be at least 8 digits long")
    private int number;


    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "dniType", nullable = false)
    private DocumentType type;
}
