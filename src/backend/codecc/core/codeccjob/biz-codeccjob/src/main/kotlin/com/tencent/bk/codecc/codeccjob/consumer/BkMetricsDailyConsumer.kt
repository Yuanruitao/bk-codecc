package com.tencent.bk.codecc.codeccjob.consumer

import com.tencent.bk.codecc.codeccjob.service.BkMetricsService
import com.tencent.devops.common.web.mq.EXCHANGE_BK_METRICS_DAILY_TRIGGER
import com.tencent.devops.common.web.mq.QUEUE_BK_METRICS_DAILY_TRIGGER
import com.tencent.devops.common.web.mq.ROUTE_BK_METRICS_DAILY_TRIGGER
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.Exchange
import org.springframework.amqp.rabbit.annotation.Queue
import org.springframework.amqp.rabbit.annotation.QueueBinding
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BkMetricsDailyConsumer @Autowired constructor(
    private val bkMetricsService: BkMetricsService
) {
    companion object {
        private val logger = LoggerFactory.getLogger(BkMetricsDailyConsumer::class.java)
    }

    @RabbitListener(
        bindings = [QueueBinding(
            key = arrayOf(ROUTE_BK_METRICS_DAILY_TRIGGER),
            value = Queue(value = QUEUE_BK_METRICS_DAILY_TRIGGER, durable = "true"),
            exchange = Exchange(value = EXCHANGE_BK_METRICS_DAILY_TRIGGER, durable = "true", delayed = "true")
        )]
    )
    fun statisticDaily() {
        try {
            // todo:防重触发
            logger.info("BkMetricsDailyConsumer begin")
            bkMetricsService.statistic(null)
            logger.info("BkMetricsDailyConsumer end")
        } catch (t: Throwable) {
            logger.error("BkMetricsDailyConsumer error", t)
        }
    }
}
