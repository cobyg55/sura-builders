package com.sura.builders.domain.repository;

import com.sura.builders.domain.model.ConstructionCycle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConstructionCycleRepository  extends JpaRepository<ConstructionCycle, Long> {

    Optional<ConstructionCycle> findByName(String name);
}
