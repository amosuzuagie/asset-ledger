package com.company.assetmgmt.service.impl;

import com.company.assetmgmt.dto.DashboardSummary;
import com.company.assetmgmt.repository.AssetRepository;
import com.company.assetmgmt.repository.projection.CountByKeyProjection;
import com.company.assetmgmt.repository.projection.SumByKeyProjection;
import com.company.assetmgmt.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final AssetRepository assetRepository;

    @Override
    public DashboardSummary getDashboardSummary() {
        return new DashboardSummary(
                assetRepository.count(),
                assetRepository.totalAssetValue(),
                toCountMap(assetRepository.countAssetsByStatus()),
                toCountMap(assetRepository.countAssetsByClass()),
                toCountMap(assetRepository.countAssetsByBranch()),
                toSumMap(assetRepository.sumAssetValueByClass()),
                toSumMap(assetRepository.sumAssetValueByBranch())
        );
    }

    private Map<String, Long> toCountMap(List<CountByKeyProjection> list) {
        return list.stream()
                .collect(Collectors.toMap(
                        CountByKeyProjection::getKey,
                        CountByKeyProjection::getTotal
                ));
    }

    private Map<String, BigDecimal> toSumMap(List<SumByKeyProjection> list) {
        return list.stream()
                .collect(Collectors.toMap(
                        SumByKeyProjection::getKey,
                        SumByKeyProjection::getTotal
                ));
    }
}
