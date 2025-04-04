package com.tencent.bk.codecc.task.pojo

import com.tencent.devops.common.constant.ComConstants
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("流水线触发请求体")
data class TriggerPipelineReq(
    @ApiModelProperty("仓库路径")
    val gitUrl: String?,
    @ApiModelProperty("分支")
    val branch: String?,
    @ApiModelProperty("commitId")
    val commitId: String?,
    @ApiModelProperty("版本号")
    val revision: String?,
    @ApiModelProperty("工蜂项目id")
    val gongfengProjectId: Int?,
    @ApiModelProperty("逻辑仓标识")
    val logicRepo: String? = null,
    @ApiModelProperty("仓库类型")
    val repoType: String?,
    @ApiModelProperty("是否显示告警")
    val defectDisplay: Boolean?,
    @ApiModelProperty("是否校验工蜂项目")
    val checkGongfengProject: Boolean? = false,
    @ApiModelProperty("是否采用yml文件进行流水线编排")
    val useYml: Boolean? = false,
    @ApiModelProperty("指定规则集包类型")
    val checkerSetPackageType: String?,
    @ApiModelProperty("指定开源扫描规则集模型")
    val checkerSetRange: List<OpenSourceCheckerSetModel>?,
    @ApiModelProperty("指定扫描集群")
    val codeccDispatchRoute: ComConstants.CodeCCDispatchRoute?,
    @ApiModelProperty("codecc流水线模型")
    val codeCCPipelineReq: CodeCCPipelineReq,
    @ApiModelProperty("支持指定git仓库目录，多个目录用英文逗号分隔")
    val includePath: String?,
    @ApiModelProperty("扫描任务owner")
    val taskOwner: String? = null,
    @ApiModelProperty("仓库 namespace owner")
    val namespaceOwners: String? = null,
    @ApiModelProperty("仓库 project owner")
    val projectOwners: String? = null,
    @ApiModelProperty("一级部门ID")
    val bgId: Long? = null,
    @ApiModelProperty("二级部门ID")
    val deptId: Long? = null,
    @ApiModelProperty("三级部门ID")
    val centerId: Long? = null,
    @ApiModelProperty("四级部门ID")
    val groupId: Long? = null,
    @ApiModelProperty("是否忽略分支参数来识别同一个任务")
    val branchIgnore: Boolean? = null,
    @ApiModelProperty("额外的仓库信息")
    val extRepoInfo: Map<String, Any>? = null
) {
    constructor(
        gitUrl: String?,
        branch: String?,
        gongfengProjectId: Int?,
        logicRepo: String?,
        repoType: String?,
        defectDisplay: Boolean?,
        checkGongfengProject: Boolean?,
        useYml: Boolean?,
        checkerSetRange: List<OpenSourceCheckerSetModel>?,
        codeccDispatchRoute: ComConstants.CodeCCDispatchRoute?,
        codeCCPipelineReq: CodeCCPipelineReq
    ) : this(
        gitUrl,
        branch,
        null,
        null,
        gongfengProjectId,
        logicRepo,
        repoType,
        defectDisplay,
        checkGongfengProject,
        useYml,
        null,
        checkerSetRange,
        codeccDispatchRoute,
        codeCCPipelineReq,
        null
    )

    constructor(
        gitUrl: String?,
        branch: String?,
        gongfengProjectId: Int?,
        taskOwner: String?,
        namespaceOwners: String?,
        projectOwners: String?,
        codeccDispatchRoute: ComConstants.CodeCCDispatchRoute?,
        extRepoInfo: Map<String, Any>?,
        codeCCPipelineReq: CodeCCPipelineReq
    ) : this(
        gitUrl,
        branch,
        null,
        null,
        gongfengProjectId,
        null,
        null,
        null,
        false,
        false,
        null,
        null,
        codeccDispatchRoute,
        codeCCPipelineReq,
        null,
        taskOwner,
        namespaceOwners,
        projectOwners,
        null,
        null,
        null,
        null,
        null,
        extRepoInfo
    )
}
