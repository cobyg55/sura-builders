package com.sura.builders.domain.repository;

import com.sura.builders.domain.model.Builders;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BuildersRepository  extends JpaRepository<Builders, Long> {

    @Query(value = "SELECT nextval('seq_builders_code')", nativeQuery = true)
    Long getBuildersCodeSeq();

    List<Builders> findAllByStatus(String status);

    Optional<Builders> findByCode(String code);

}
