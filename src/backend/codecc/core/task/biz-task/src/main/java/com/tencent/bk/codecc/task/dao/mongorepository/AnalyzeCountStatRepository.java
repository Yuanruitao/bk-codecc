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

package com.tencent.bk.codecc.task.dao.mongorepository;

import com.tencent.bk.codecc.task.model.AnalyzeCountStatEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 工具分析次数统计持久层
 *
 * @version V1.0
 * @date 2021/01/06
 */

@Repository
public interface AnalyzeCountStatRepository extends MongoRepository<AnalyzeCountStatEntity, String> {
    List<AnalyzeCountStatEntity> findByDateAndDataFromInAndToolName(
            String date, Set<String> createFrom, String toolName);
}
