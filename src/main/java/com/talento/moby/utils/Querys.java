package com.talento.moby.utils;

public class Querys {

    private Querys() {
        throw new IllegalStateException("Utility class");
    }


    public static final String TECHNOLOGIES_AND_YEARS_OF_EXPERIENCE_BY_CANDIDATE = "SELECT t.technology_id, t.name, t.version, te.expertise_in_years FROM technologies_expertise te JOIN technologies t USING (technology_id) JOIN candidates c USING (candidate_id) WHERE te.candidate_id=?1";
}
