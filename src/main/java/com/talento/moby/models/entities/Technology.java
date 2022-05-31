package com.talento.moby.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "technologies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "technologyId")
    private Long id;

    @Column(name = "name", nullable = false)

    private String name;

    @Column(name = "version")
    private int version;

}
