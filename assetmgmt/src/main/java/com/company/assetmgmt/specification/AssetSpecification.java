package com.company.assetmgmt.specification;

import com.company.assetmgmt.model.*;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.UUID;

public final class AssetSpecification {

    private AssetSpecification () {}

    public static Specification<Asset> hasAssetCodeLike(String assetCode) {
        return (root, query, cb) ->
                assetCode == null ? null : cb.like(
                        cb.lower(root.get("assetCode")), "%" + assetCode.toLowerCase() + "%");
    }

    public static Specification<Asset> hasDescriptionLike(String description) {
        return (root, query, cb) ->
                description == null ? null : cb.like(
                        cb.lower(root.get("description")), "%" + description.toLowerCase() + "%"
                );
    }

    public static Specification<Asset> hasAssetClass(AssetClass assetClass) {
        return (root, query, cb) ->
                assetClass == null ? null : cb.equal(root.get("assetClass"), assetClass);
    }

    public static Specification<Asset> hasStatus(AssetStatus status) {
        return (root, query, cb) ->
                status == null ? null : cb.equal(root.get("status"), status);
    }

    public static Specification<Asset> hasSubsidiary(String subsidiary) {
        return (root, query, cb) ->
                subsidiary == null ? null : cb.equal(cb.lower(root.get("subsidiary")), subsidiary.toLowerCase());
    }

    public static Specification<Asset> hasCategory(UUID categoryId) {
        return (root, query, cb) -> {
            if (categoryId == null) return null;
            Join<Asset, AssetCategory> join = root.join("category", JoinType.LEFT);
            return cb.equal(join.get("id"), categoryId);
        };
    }

    public static Specification<Asset> hasBranch(UUID branchId) {
        return (root, query, cb) -> {
            if (branchId == null) return null;
            Join<Asset, Branch> join = root.join("branch", JoinType.LEFT);
            return cb.equal(join.get("id"), branchId);
        };
    }

    public static Specification<Asset> acquireBetween(LocalDate from, LocalDate to) {
        return (root, query, cb) -> {
            if (from == null && to == null) return null;
            if (from != null && to != null) {
                return cb.between(root.get("dateOfAcquisition"), from, to);
            }
            if (from != null) {
                return cb.greaterThanOrEqualTo(root.get("dateOfAcquisition"), from);
            }
            return cb.lessThanOrEqualTo(root.get("dateOfAcquisition"), to);
        };
    }
}
