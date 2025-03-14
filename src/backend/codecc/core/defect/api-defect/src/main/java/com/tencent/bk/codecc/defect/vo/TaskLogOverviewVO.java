package com.tencent.bk.codecc.defect.vo;

import com.tencent.devops.common.api.analysisresult.ToolLastAnalysisResultVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

@Data
@ApiModel("工具执行记录请求对象")
public class TaskLogOverviewVO {

    @ApiModelProperty(value = "工具分析信息")
    List<TaskLogVO> taskLogVOList;
    @ApiModelProperty(value = "代码库字符串信息")
    List<String> repoInfoStrList;
    @ApiModelProperty(value = "本次工具分析结果")
    List<ToolLastAnalysisResultVO> lastAnalysisResultVOList;
    @ApiModelProperty(value = "任务ID")
    private Long taskId;
    @ApiModelProperty(value = "构建号")
    private String buildId;
    @ApiModelProperty(value = "构建号")
    private String buildNum;
    @ApiModelProperty(value = "任务状态")
    private Integer status;
    @ApiModelProperty("代码库版本号")
    private String version;
    @ApiModelProperty("分析开始时间")
    private Long startTime;
    @ApiModelProperty("分析结束时间")
    private Long endTime;
    @ApiModelProperty("扫描耗时")
    private Long elapseTime;
    @ApiModelProperty("分析触发用户")
    private String buildUser;
    @ApiModelProperty(value = "工具集", required = true)
    private List<String> tools;
    @ApiModelProperty(value = "未过滤的工具集")
    private List<String> originScanTools;
    @ApiModelProperty(value = "插件错误码", required = true)
    private Integer pluginErrorCode;
    @ApiModelProperty(value = "插件错误类型", required = true)
    private Integer pluginErrorType;
    @ApiModelProperty(value = "自动识别语言扫描标识，标识本次扫描是识别语言")
    private Boolean autoLanguageScan;
}
