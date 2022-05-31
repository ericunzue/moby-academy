package com.talento.moby.models.dto;

import com.talento.moby.models.entities.Document;
import com.talento.moby.models.entities.Technology;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Valid
public class CandidateDto {

    private Long id;
    
    @NotBlank()
    @Size(min = 1, message = "name cannot be empty")
    private String name;

    @NotBlank()
    @Size(min = 1, message = "surname cannot be empty")
    private String surname;

    @Valid
    private Document dni;

    @Past(message = "birthday cannot be future")
    private LocalDate birthday;


    private List<Technology> technologies;
}
