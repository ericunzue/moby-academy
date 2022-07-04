package com.talento.moby.testUtils;

import com.talento.moby.models.dto.CandidateDto;
import com.talento.moby.models.dto.CandidateWithTechnologiesDto;
import com.talento.moby.models.dto.TechnologyDto;
import com.talento.moby.models.dto.TechnologyWithCandidatesDto;
import com.talento.moby.models.entities.Candidate;
import com.talento.moby.models.entities.Document;
import com.talento.moby.models.entities.Technology;
import com.talento.moby.models.enums.DocumentType;
import com.talento.moby.models.projections.CandidatesExpertiseProjection;
import com.talento.moby.models.projections.TechnologyExpertiseProjection;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.LongStream;

public class TestEntityFactory {

    //Random Birthday
    public static LocalDate randomBirthday() {
        return LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 70))));
    }

    // Candidates
    public static List<Candidate> get_candidates() {
        List<Candidate> candidates = new ArrayList<>();

        LongStream.range(0, 10).forEach(i -> {
            Candidate c = new Candidate(i, "Name" + i, "Surname" + i, randomBirthday(), get_document(i));
            candidates.add(c);
        });
        return candidates;
    }

    public static Document get_document() {
        return Document.builder()
                .id(1L)
                .number(31)
                .type(DocumentType.DNI)
                .build();
    }

    public static Document get_document(Long number) {
        return Document.builder()
                .id(number)
                .number((int) (31 + number))
                .type(DocumentType.DNI)
                .build();
    }

    public static Candidate get_candidate() {
        return Candidate.builder()
                .id(1L)
                .name("Marta")
                .surname("Petrona")
                .birthDate(null)
                .dni(get_document())
                .build();
    }

    public static CandidateDto get_candidate_dto() {
        return CandidateDto.builder()
                .name("Pedro")
                .surname("Pedrin")
                .birthday(null)
                .dni(get_document())
                .build();
    }

    public static CandidateWithTechnologiesDto get_candidate_with_technologies() {
        return CandidateWithTechnologiesDto.builder()
                .id(1L)
                .name("Marta")
                .surname("Petrona")
                .technologies(get_projection_technologies_list())
                .build();
    }

    //Technologies

    public static List<TechnologyExpertiseProjection> get_projection_technologies_list() {
        List<TechnologyExpertiseProjection> technologies = new ArrayList<>();

        LongStream.range(0, 5).forEach(i -> {

            technologies.add(get_technology_expertise_projection(i));
        });
        return technologies;

    }

    public static TechnologyExpertiseProjection get_technology_expertise_projection(Long id) {

        return new TechnologyExpertiseProjection() {
            @Override
            public Long getTechnologyId() {
                return id;
            }

            @Override
            public void setTechnologyId(Long technologyId) {

            }

            @Override
            public String getName() {
                return "Technology";
            }

            @Override
            public void setName(String name) {

            }

            @Override
            public int getVersion() {
                return (int) (id + 3);
            }

            @Override
            public void setVersion(int version) {

            }

            @Override
            public int getExpertise() {
                return Math.toIntExact(id);
            }

            @Override
            public void setExpertise(int expertise) {

            }
        };

    }

    public static List<Technology> get_technologies() {
        List<Technology> technologies = new ArrayList<>();

        LongStream.range(0, 5).forEach(i -> {
            Technology c = new Technology(i, "Name" + i, (int) (i + 3));
            technologies.add(c);
        });
        return technologies;
    }

    public static Technology get_technology() {
        return Technology.builder()
                .id(1L)
                .name("Java")
                .version(8)
                .build();
    }

    public static TechnologyDto get_technology_dto() {
        return TechnologyDto.builder()
                .name("Java")
                .version(8)
                .build();
    }

    public static TechnologyWithCandidatesDto get_technologies_with_candidates() {
        return TechnologyWithCandidatesDto.builder()
                .technology(get_technology())
                .candidates(get_candidates_expertise_projection_list())
                .build();
    }

    public static List<CandidatesExpertiseProjection> get_candidates_expertise_projection_list() {
        List<CandidatesExpertiseProjection> candidatesExpertise = new ArrayList<>();
        LongStream.range(0, 5).forEach(i -> {
            candidatesExpertise.add(get_candidates_expertise_projection(i));
        });
        return candidatesExpertise;
    }

    public static CandidatesExpertiseProjection get_candidates_expertise_projection(Long id) {
        return new CandidatesExpertiseProjection() {
            @Override
            public String getName() {
                return "Candidate name" + id;
            }

            @Override
            public void setName(String name) {

            }

            @Override
            public int getExpertise() {
                return 2;
            }

            @Override
            public void setExpertise(int expertise) {

            }
        };
    }

}
