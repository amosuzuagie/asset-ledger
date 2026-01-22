package com.company.assetmgmt.dto.report;

import lombok.Data;

@Data
public class CountReport {
    private String label;
    private Long count;

    public CountReport(String label, Long count) {
        this.label = label;
        this.count = count;
    }
}
