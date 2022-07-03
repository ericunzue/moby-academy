package com.talento.moby.repositories;

import com.talento.moby.models.entities.Technology;
import com.talento.moby.models.projections.CandidatesExpertiseProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.talento.moby.utils.Querys.CANDIDATES_BY_TECHNOLOGY;
import static com.talento.moby.utils.Querys.GET_TECHNOLOGY_ID_BY_NAME_AND_VERSION;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    @Override
    Optional<Technology> findById(Long aLong);

    @Query(value = GET_TECHNOLOGY_ID_BY_NAME_AND_VERSION, nativeQuery = true)
    Optional<Technology> findTechnologyBy(String name, int version);

    @Query(value = CANDIDATES_BY_TECHNOLOGY, nativeQuery = true)
    List<CandidatesExpertiseProjection> getCandidates(Long technologyId);
}
