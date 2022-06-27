package com.talento.moby.models.projections;

import org.springframework.beans.factory.annotation.Value;

public interface TechnologyExpertiseProjection {

    @Value("#{target.technology_id}")
    Long getTechnologyId();

    @Value("#{target.technology_id}")
    void setTechnologyId(Long technologyId);

    @Value("#{target.name}")
    String getName();

    @Value("#{target.name}")
    void setName(String name);

    @Value("#{target.version}")
    int getVersion();

    @Value("#{target.version}")
    void setVersion(int version);

    @Value("#{target.expertise_in_years}")
    int getExpertise();

    @Value("#{target.expertise_in_years}")
    void setExpertise(int expertise);

}
