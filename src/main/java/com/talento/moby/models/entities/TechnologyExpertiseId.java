package com.talento.moby.models.entities;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TechnologyExpertiseId that = (TechnologyExpertiseId) o;
        return Objects.equals(technology, that.technology) && Objects.equals(candidate, that.candidate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(technology, candidate);
    }
}
