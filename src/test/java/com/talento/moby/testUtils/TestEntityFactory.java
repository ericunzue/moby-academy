package com.talento.moby.testUtils;

import com.talento.moby.models.entities.Candidate;
import com.talento.moby.models.entities.Document;
import com.talento.moby.models.enums.DocumentType;

import java.time.LocalDate;
import java.time.Period;
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
        List<Candidate> candidates = null;

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


}
