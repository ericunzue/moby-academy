package com.talento.moby.repositories;

import com.talento.moby.models.entities.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    @Override
    Optional<Technology> findById(Long aLong);
}
