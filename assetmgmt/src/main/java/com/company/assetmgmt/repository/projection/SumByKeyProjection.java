package com.company.assetmgmt.repository.projection;

import java.math.BigDecimal;

public interface SumByKeyProjection {
    String getKey();
    BigDecimal getTotal();
}
