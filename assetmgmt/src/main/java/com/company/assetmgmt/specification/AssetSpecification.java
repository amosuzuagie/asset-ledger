package com.company.assetmgmt.specification;

import com.company.assetmgmt.dto.AssetSearchRequest;
import com.company.assetmgmt.model.Asset;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class AssetSpecification {
    public  static Specification<Asset> build(AssetSearchRequest filter) {
        return ((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getSerialNumber() != null) {
                predicates.add(cb.like(
                        cb.lower(root.get("serialNumber")),
                        "%" + filter.getSerialNumber().toLowerCase() + "%"
                ));
            }
            if (filter.getTagId() != null) {
                predicates.add(cb.like(
                        cb.lower(root.get("tagId")),
                        "%" + filter.getTagId().toLowerCase() + "%"
                ));
            }
            if (filter.getAssetClass() != null) {
                predicates.add(cb.equal(root.get("assetClass"), filter.getAssetClass()));
            }
            if (filter.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), filter.getStatus()));
            }
            if (filter.getSubsidiary() != null) {
                predicates.add(cb.equal(root.get("subsidiary"), filter.getSubsidiary()));
            }
            if (filter.getBranchId() != null) {
                predicates.add(cb.equal(root.get("branch").get("id"), filter.getBranchId()));
            }
            if (filter.getAcquiredFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("acquisitionDate"), filter.getAcquiredFrom()));
            }
            if (filter.getAcquiredTo() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("acquisitionDate"), filter.getAcquiredTo()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
