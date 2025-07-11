/*
 * Tencent is pleased to support the open source community by making BlueKing available.
 * Copyright (C) 2017-2018 Tencent. All rights reserved.
 * Licensed under the MIT License (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * http://opensource.org/licenses/MIT
 * Unless required by applicable law or agreed to in writing, software distributed under
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tencent.bk.codecc.codeccjob.service.impl;

import com.tencent.bk.codecc.defect.model.defect.CommonDefectEntity;
import com.tencent.devops.common.util.PathUtils;
import kotlin.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 通用工具的路径屏蔽
 *
 * @version V1.0
 * @date 2019/11/1
 */
@Service("KLOCWORKFilterPathBizService")
@Slf4j
public class KlocworkFilterPathBizServiceImpl extends CommonFilterPathBizServiceImpl
{
    @Override
    protected Boolean checkIfMaskByPath(CommonDefectEntity defectEntity, Set<String> filterPaths)
    {
        Set<String> filterPathSet = PathUtils.convertPathsToLowerCase(filterPaths);
        Pair<Boolean, String> pair = PathUtils.checkIfMaskByPath(
                defectEntity.getFilePath().toLowerCase(), filterPathSet);
        if (pair.getFirst()) {
            defectEntity.setMaskPath(pair.getSecond());
        }
        return pair.getFirst();
    }
}
