package com.sura.builders.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class ConstructionCycle {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "construction_cycle_generator")
    @SequenceGenerator(name = "construction_cycle_generator", sequenceName = "construction_cycle_seq", allocationSize = 1)
    private long id;
    private String name;
    private int totalDays;
    private LocalDateTime createdAt;

}
