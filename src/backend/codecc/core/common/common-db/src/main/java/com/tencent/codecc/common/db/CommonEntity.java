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

package com.tencent.codecc.common.db;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 公共实体类
 *
 * @version V1.0
 * @date 2019/4/25
 */
@Data
public class CommonEntity {

    private static final String DEFAULT_USER = "system";

    @Id
    private String entityId;

    /**
     * 创建时间
     */
    @Field("create_date")
    private Long createdDate;

    /**
     * 创建人
     */
    @Field("created_by")
    private String createdBy;

    /**
     * 更新时间
     */
    @Field("updated_date")
    private Long updatedDate;

    /**
     * 更新人
     */
    @Field("updated_by")
    private String updatedBy;

    /**
     * 填充审计信息
     *
     * @param createdBy
     */
    public void applyAuditInfoOnCreate(String createdBy) {
        long now = System.currentTimeMillis();
        this.createdBy = createdBy;
        this.createdDate = now;
        this.updatedBy = createdBy;
        this.updatedDate = now;
    }

    public void applyAuditInfoOnCreate() {
        applyAuditInfoOnCreate(DEFAULT_USER);
    }

    /**
     * 填充审计信息
     *
     * @param updatedBy
     */
    public void applyAuditInfoOnUpdate(String updatedBy) {
        this.updatedBy = updatedBy;
        this.updatedDate = System.currentTimeMillis();
    }

    public void applyAuditInfoOnUpdate() {
        applyAuditInfoOnUpdate(DEFAULT_USER);
    }
}
