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

package com.tencent.bk.codecc.codeccjob.consumer;

import static com.tencent.devops.common.web.mq.ConstantsKt.EXCHANGE_TASK_PERSONAL;
import static com.tencent.devops.common.web.mq.ConstantsKt.QUEUE_TASK_PERSONAL;
import static com.tencent.devops.common.web.mq.ConstantsKt.ROUTE_TASK_PERSONAL;

import com.tencent.bk.codecc.codeccjob.service.TaskPersonalStatisticService;
import com.tencent.bk.codecc.defect.vo.TaskPersonalStatisticRefreshReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 分析版本队列消费逻辑
 *
 * @version V1.0
 * @date 2019/7/16
 */
@Component
@Slf4j
public class TaskPersonalStatisticConsumer {

    @Autowired
    private TaskPersonalStatisticService taskPersonalStatisticService;

    /**
     * 刷新个人待处理
     *
     * @param mqObj
     */
    @RabbitListener(
            bindings = @QueueBinding(
                    key = ROUTE_TASK_PERSONAL,
                    value = @Queue(value = QUEUE_TASK_PERSONAL, durable = "true"),
                    exchange = @Exchange(
                            value = EXCHANGE_TASK_PERSONAL, durable = "true", delayed = "true", type = "topic"
                    )
            )
    )
    public void refreshTaskPersonalStatistic(TaskPersonalStatisticRefreshReq mqObj) {
        long taskId = mqObj != null ? mqObj.getTaskId() : 0L;
        if (taskId == 0L) {
            return;
        }

        try {
            long beginTime = System.currentTimeMillis();
            log.info("refresh task personal statistic begin, task id: {}, mq obj: {}", taskId, mqObj);

            taskPersonalStatisticService.refresh(taskId, mqObj.getExtraInfo());

            long costTime = System.currentTimeMillis() - beginTime;
            log.info("refresh task personal statistic end, task id: {}, cost: {}", taskId, costTime);
        } catch (Exception e) {
            log.error("refresh task personal statistic error, task id: {}, mq obj: {}", taskId, mqObj, e);
        }
    }
}
