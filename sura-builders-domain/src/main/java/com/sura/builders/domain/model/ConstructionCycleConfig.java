package com.sura.builders.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class ConstructionCycleConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "construction_cycle_config_generator")
    @SequenceGenerator(name = "construction_cycle_config_generator", sequenceName = "construction_cycle_config_seq", allocationSize = 1)
    private long id;
    private int dayLate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "construction_cycle_id")
    ConstructionCycle constructionCycle;
    private LocalDateTime createdAt;

}
