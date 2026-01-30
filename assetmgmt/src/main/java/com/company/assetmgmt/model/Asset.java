package com.company.assetmgmt.model;

import com.company.assetmgmt.model.enums.AssetClass;
import com.company.assetmgmt.model.enums.AssetStatus;
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
        uniqueConstraints = {@UniqueConstraint(columnNames = "serial_number")}
)
public class Asset extends BaseEntity {
    /* =======================
       BUSINESS IDENTIFICATION
     ======================= */

    @Column(name = "asset_code", nullable = false, unique = true, length = 100)
    private String assetCode;

    @Column(name = "description", nullable = false)
    private String description;

    /* =======================
       CLASSIFICATION
     ======================= */

    @Enumerated(EnumType.STRING)
    @Column(name = "asset_class", nullable = false, length = 20)
    private AssetClass assetClass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private AssetCategory category;

    /* =======================
       IDENTIFICATION DETAILS
     ======================= */

    @Column(name = "serial_number", unique = true)
    private String serialNumber;

    /* =======================
       FINANCIALS
     ======================= */

    @Column(name = "date_of_acquisition")
    private LocalDate dateOfAcquisition;

    @Column(name = "amount", precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "subsidiary", length = 50)
    private String subsidiary;

    /* =======================
       LOCATION
     ======================= */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branch branch; // nullable = in store

    /* =======================
       LIFECYCLE
     ======================= */

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private AssetStatus status;

    private boolean disposed = false;

    @Column(name = "date_of_disposal")
    private LocalDate dateOfDisposal;

    @Column(name = "cost_of_disposal", precision = 15, scale = 2)
    private BigDecimal costOfDisposal;

    @Column(name = "disposal_remark", length = 255)
    private String disposalRemark;

    /* =======================
       REMARKS
     ======================= */

    @Column(name = "remark", length = 500)
    private String remark;

    /* =======================
       HELPERS
     ======================= */

    public boolean isInStore() {
        return branch == null && status == AssetStatus.IN_STORE;
    }

    public boolean isDisposed() {
        return status == AssetStatus.DISPOSED;
    }
}
