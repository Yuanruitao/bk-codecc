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

import com.tencent.bk.codecc.task.model.TaskFailRecordEntity;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 开源扫描失败重试持久
 *
 * @version V1.0
 * @date 2020/7/13
 */
@Repository
public interface TaskFailRecordRepository extends MongoRepository<TaskFailRecordEntity, String> {

    List<TaskFailRecordEntity> findByUploadTimeGreaterThanAndTimeCostLessThanAndProjectIdNotOrderByUploadTimeAsc(
            Long uploadTime, Long timeCost, String projectId);
}
