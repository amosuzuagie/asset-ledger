package com.company.assetmgmt.model;

import com.company.assetmgmt.model.enums.AssetClass;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "asset_categories")
@SQLRestriction("deleted = false")
public class AssetCategory extends BaseEntity{
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "asset_class", nullable = false)
    private AssetClass assetClass;

    @Column(length = 500)
    private String description;
}
