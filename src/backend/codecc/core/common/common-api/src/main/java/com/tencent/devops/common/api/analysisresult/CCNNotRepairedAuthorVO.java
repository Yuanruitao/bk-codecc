package com.tencent.devops.common.api.analysisresult;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CCNNotRepairedAuthorVO extends BaseRiskNotRepairedAuthorVO {
    /**
     * 低风险级别告警数
     */
    private int lowCount;
}
