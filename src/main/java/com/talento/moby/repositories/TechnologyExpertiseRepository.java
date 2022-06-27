package com.talento.moby.repositories;

import com.talento.moby.models.entities.TechnologyExpertise;
import com.talento.moby.models.entities.TechnologyExpertiseId;
import com.talento.moby.models.projections.TechnologyExpertiseProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.talento.moby.utils.Querys.TECHNOLOGIES_AND_YEARS_OF_EXPERIENCE_BY_CANDIDATE;

@Repository
public interface TechnologyExpertiseRepository extends JpaRepository<TechnologyExpertise, TechnologyExpertiseId> {


    @Query(value = TECHNOLOGIES_AND_YEARS_OF_EXPERIENCE_BY_CANDIDATE, nativeQuery = true)
    List<TechnologyExpertiseProjection> getTechnologiesAndYearsOfExperienceByCandidate(Long candidateId);
}
