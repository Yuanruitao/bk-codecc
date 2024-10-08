package com.tencent.bk.codecc.defect.vo.redline;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * 编译类工具红线告警
 *
 * @version V1.0
 * @date 2019/12/6
 */
@Data
@ApiModel("编译类工具红线告警")
public class RLCompileDefectVO
{
    @ApiModelProperty("遗留严重告警数")
    private long remainSerious;

    @ApiModelProperty("遗留一般告警数")
    private long remainNormal;

    @ApiModelProperty("遗留提示告警数")
    private long remainPrompt;

    @ApiModelProperty("新严重告警数")
    private long newSerious;

    @ApiModelProperty("新一般告警数")
    private long newNormal;

    @ApiModelProperty("新提示告警数")
    private long newPrompt;

    @ApiModelProperty("历史严重告警数（遗留 - 新）")
    private long historySerious;

    @ApiModelProperty("历史一般告警数（遗留 - 新）")
    private long historyNormal;

    @ApiModelProperty("历史提示告警数（遗留 - 新）")
    private long historyPrompt;

    @ApiModelProperty("规则包告警数")
    private Map<String, Long> checkerPkgCounts;
}
