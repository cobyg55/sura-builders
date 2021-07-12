package com.sura.builders.domain.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
public class Builders {

    private long id;
    private String code;
    private String name;
    private String status;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "builders_construction_cycles",
            joinColumns = @JoinColumn(name = "builders_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "construction_cycles_config_id", referencedColumnName = "id"))
    private Collection<ConstructionCycleConfig> constructionCycleConfigs;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdAt;
}
