package com.tencent.bk.codecc.codeccjob.consumer;

import com.tencent.bk.codecc.codeccjob.service.DataSeparationService;
import com.tencent.devops.common.constant.ComConstants;
import com.tencent.devops.common.service.IConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataSeparationWarmUpConsumer implements IConsumer<Long> {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private DataSeparationService dataSeparationService;

    @Override
    public void consumer(Long taskId) {
        try {
            log.info("DataSeparationWarmUpConsumer begin, task id: {}", taskId);
            // 紧急开关
            if ("1".equals(redisTemplate.opsForValue().get(ComConstants.SWITCH_FOR_DATA_SEPARATION_WARM_UP))) {
                log.info("SWITCH_FOR_DATA_SEPARATION_WARM_UP is open");
                return;
            }

            dataSeparationService.warmUp(taskId);
            log.info("DataSeparationWarmUpConsumer end, task id: {}", taskId);
        } catch (Throwable t) {
            log.error("DataSeparationWarmUpConsumer fail, task id: {}", taskId, t);
        }
    }
}