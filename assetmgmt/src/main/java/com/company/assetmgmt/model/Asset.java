package com.company.assetmgmt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction("deleted = false")
@Table(
        name = "assets",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "serial_number"),
                @UniqueConstraint(columnNames = "tag_id")
        }
)
public class Asset extends BaseEntity {
    // =========================
    // Identification
    // =========================

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "asset_class", nullable = false)
    private AssetClass assetClass;

    @Column(name = "serial_number", nullable = false, unique = true)
    private String serialNumber;

    @Column(name = "tag_id", nullable = false, unique = true)
    private String tagId;

    // =========================
    // Lifecycle
    // =========================

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssetStatus status;

    // =========================
    // Financials
    // =========================

    @Column(name = "date_of_acquisition", nullable = false)
    private LocalDate acquisitionDate;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column
    private String remark;

    @Column(name = "date_of_disposal")
    private LocalDate disposalDate;

    @Column(name = "cost_of_disposal", precision = 15, scale = 2)
    private BigDecimal disposalCost;

    @Column(nullable = false)
    private String subsidiary;

    // =========================
    // Relationships
    // =========================

    /**
     * Nullable:
     * - Asset may be in store
     * - Asset may be assigned later
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branch branch;
}
