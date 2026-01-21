package com.company.assetmgmt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "branches")
public class Branch extends BaseEntity{
    @Column(nullable = false)
    private String name;

//    @Column(nullable = false, unique = true)
//    private String code;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String location;

    @Column
    private String address;
}
