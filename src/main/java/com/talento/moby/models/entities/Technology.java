package com.talento.moby.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity(name = "technologies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "technologyId")
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotEmpty(message = "Name may not be blank")
    private String name;

    @Column(name = "version")
    private int version;

}
