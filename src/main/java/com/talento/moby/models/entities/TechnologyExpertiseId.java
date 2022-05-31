package com.talento.moby.models.entities;

import java.io.Serializable;

public class TechnologyExpertiseId implements Serializable {

    private Long technology;
    private Long candidate;

    public TechnologyExpertiseId() {
    }

    public TechnologyExpertiseId(Long technology, Long candidate) {
        this.technology = technology;
        this.candidate = candidate;
    }

    public Long getTechnology() {
        return technology;
    }

    public void setTechnology(Long technology) {
        this.technology = technology;
    }

    public Long getCandidate() {
        return candidate;
    }

    public void setCandidate(Long candidate) {
        this.candidate = candidate;
    }
}
