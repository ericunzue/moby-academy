package com.talento.moby.repositories;

import com.talento.moby.models.entities.TechnologyExpertise;
import com.talento.moby.models.entities.TechnologyExpertiseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyExpertiseRepository extends JpaRepository<TechnologyExpertise, TechnologyExpertiseId> {

}
