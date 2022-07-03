package com.talento.moby.models.projections;

import org.springframework.beans.factory.annotation.Value;

public interface CandidatesExpertiseProjection {


    @Value("#{target.name}")
    String getName();

    @Value("#{target.name}")
    void setName(String name);

    @Value("#{target.expertise_in_years}")
    int getExpertise();

    @Value("#{target.expertise_in_years}")
    void setExpertise(int expertise);


}
