package com.talento.moby.utils;

public class Querys {

    private Querys() {
        throw new IllegalStateException("Utility class");
    }
    public static final String TECHNOLOGIES_AND_YEARS_OF_EXPERIENCE_BY_CANDIDATE = "SELECT t.technology_id, t.name, t.version, te.expertise_in_years FROM technologies_expertise te JOIN technologies t USING (technology_id) JOIN candidates c USING (candidate_id) WHERE te.candidate_id=?1";
    public static final String REMOVE_CANDIDATE_EXPERIENCE = "DELETE FROM technologies_expertise WHERE candidate_id=?1 AND technology_id=?2";


}

