package com.talento.moby.models.entities;

import java.io.Serializable;

public class TechnologyExpertiseId implements Serializable {

    private int technology;
    private int candidate;

    public TechnologyExpertiseId() {
    }

    public TechnologyExpertiseId(int technology, int candidate) {
        this.technology = technology;
        this.candidate = candidate;
    }

    public int getTechnology() {
        return technology;
    }

    public void setTechnology(int technology) {
        this.technology = technology;
    }

    public int getCandidate() {
        return candidate;
    }

    public void setCandidate(int candidate) {
        this.candidate = candidate;
    }
}
