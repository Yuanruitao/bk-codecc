/*
 * Tencent is pleased to support the open source community by making BK-CODECC 蓝鲸代码检查平台 available.
 *
 * Copyright (C) 2019 Tencent.  All rights reserved.
 *
 * BK-CODECC 蓝鲸代码检查平台 is licensed under the MIT license.
 *
 * A copy of the MIT License is included in this file.
 *
 *
 * Terms of the MIT License:
 * ---------------------------------------------------
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
 * NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.tencent.bk.codecc.defect.service;

import com.tencent.bk.codecc.defect.model.SnapShotEntity;
import com.tencent.bk.codecc.defect.vo.common.SnapShotVO;

/**
 * 快照服务接口
 *
 * @version V1.0
 * @date 2019/6/28
 */
public interface SnapShotService {
    /**
     * 保存快照信息
     *
     * @param taskId
     * @param projectId
     * @param pipelineId
     * @param buildId
     * @param resultStatus
     * @param resultMessage
     * @param toolName
     */
    SnapShotEntity saveToolBuildSnapShot(long taskId, String projectId, String pipelineId, String buildId,
                                         String resultStatus, String resultMessage, String toolName);

    /**
     * 查找快照信息
     *
     * @param projectId
     * @param buildId
     * @param taskId
     */
    SnapShotVO getTaskToolBuildSnapShot(String projectId, String buildId, long taskId);

    /**
     * 更新元数据上传状态
     *
     * @param projectId
     * @param buildId
     * @param status
     * @return
     */
    void updateMetadataReportStatus(String projectId, String buildId, long taskId, boolean status);

    SnapShotVO getSnapShotVO(String projectId, Long taskId, String buildId);

    void allocateSnapshotOnBuildStart(
            String projectId,
            String pipelineId,
            Long taskId,
            String buildId,
            Long buildFlag
    );
}
