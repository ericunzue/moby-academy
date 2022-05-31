package com.talento.moby.repositories;

import com.talento.moby.models.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    @Override
    Optional<Candidate> findById(Long aLong);
}
