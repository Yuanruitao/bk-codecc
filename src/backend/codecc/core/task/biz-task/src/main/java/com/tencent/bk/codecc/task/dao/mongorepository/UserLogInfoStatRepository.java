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

import com.tencent.bk.codecc.task.model.UserLogInfoStatEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 用户登录统计持久类
 *
 * @version V1.0
 * @date 2020/10/20
 */
@Repository
public interface UserLogInfoStatRepository extends MongoRepository<UserLogInfoStatEntity, String> {

    /**
     * 根据用户名查询
     *
     * @param userName RTX
     * @return entity
     */
    UserLogInfoStatEntity findFirstByUserName(String userName);

    List<UserLogInfoStatEntity> findByUserNameIn(Set<String> userNames);

}
