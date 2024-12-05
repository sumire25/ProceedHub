package com.mistysoft.proceedhub.modules.scholarship.infrastructure;

import com.mistysoft.proceedhub.modules.scholarship.domain.Requirement;
import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "scholarships")
@Data
public class ScholarshipEntity {

    @Id
    private final String id;

    @Column(nullable = false)
    private final String title;
    
    @Column(nullable = false)
    private final String description;

    @Column(nullable = false)
    private final ZonedDateTime date;

    @Column(nullable = false)
    private final String image;

    @Column(nullable = false)
    private final String country;

    @Column(nullable = false)
    private final String continent;

    @Column(nullable = false)
    private final String moreInfo;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "scholarship_requirements", joinColumns = @JoinColumn(name = "scholarship_id"))
    private final Set<Requirement> requirements;

}
