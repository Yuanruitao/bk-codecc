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

package com.tencent.bk.codecc.defect.config;

import com.tencent.bk.codecc.defect.condition.AsyncReportCondition;
import com.tencent.bk.codecc.defect.consumer.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import static com.tencent.devops.common.web.mq.ConstantsKt.EXCHANGE_DEFECT_COMMIT_CCN_NEW;
import static com.tencent.devops.common.web.mq.ConstantsKt.EXCHANGE_DEFECT_COMMIT_CLOC_NEW;
import static com.tencent.devops.common.web.mq.ConstantsKt.EXCHANGE_DEFECT_COMMIT_DUPC_NEW;
import static com.tencent.devops.common.web.mq.ConstantsKt.EXCHANGE_DEFECT_COMMIT_LINT_NEW;
import static com.tencent.devops.common.web.mq.ConstantsKt.EXCHANGE_DEFECT_COMMIT_SCA_NEW;
import static com.tencent.devops.common.web.mq.ConstantsKt.EXCHANGE_DEFECT_COMMIT_STAT_NEW;
import static com.tencent.devops.common.web.mq.ConstantsKt.QUEUE_DEFECT_COMMIT_CCN_NEW;
import static com.tencent.devops.common.web.mq.ConstantsKt.QUEUE_DEFECT_COMMIT_CLOC_NEW;
import static com.tencent.devops.common.web.mq.ConstantsKt.QUEUE_DEFECT_COMMIT_DUPC_NEW;
import static com.tencent.devops.common.web.mq.ConstantsKt.QUEUE_DEFECT_COMMIT_LINT_NEW;
import static com.tencent.devops.common.web.mq.ConstantsKt.QUEUE_DEFECT_COMMIT_SCA_NEW;
import static com.tencent.devops.common.web.mq.ConstantsKt.QUEUE_DEFECT_COMMIT_STAT_NEW;
import static com.tencent.devops.common.web.mq.ConstantsKt.ROUTE_DEFECT_COMMIT_CCN_NEW;
import static com.tencent.devops.common.web.mq.ConstantsKt.ROUTE_DEFECT_COMMIT_CLOC_NEW;
import static com.tencent.devops.common.web.mq.ConstantsKt.ROUTE_DEFECT_COMMIT_DUPC_NEW;
import static com.tencent.devops.common.web.mq.ConstantsKt.ROUTE_DEFECT_COMMIT_LINT_NEW;
import static com.tencent.devops.common.web.mq.ConstantsKt.ROUTE_DEFECT_COMMIT_SCA_NEW;
import static com.tencent.devops.common.web.mq.ConstantsKt.ROUTE_DEFECT_COMMIT_STAT_NEW;

/**
 * 正常项目队列定义及绑定交换器
 *
 * @version V1.0
 * @date 2019/5/27
 */
@Configuration
@Slf4j
@Conditional(AsyncReportCondition.class)
public class CommitDefectNormalProjRabbitMQConfig extends AbstractCommitDefectRabbitMQConfig
{
    /**
     * 并发消费者数
     */
    private static final int CONCURRENT_CONSUMERS = 16;

    /**
     * 最大并发消费者数
     */
    private static final int MAX_CONCURRENT_CONSUMERS = 16;

    @Bean
    public Queue lintNewCommitQueue()
    {
        return new Queue(QUEUE_DEFECT_COMMIT_LINT_NEW);
    }

    @Bean
    public DirectExchange lintNewDirectExchange()
    {
        DirectExchange directExchange = new DirectExchange(EXCHANGE_DEFECT_COMMIT_LINT_NEW);
        directExchange.setDelayed(true);
        return directExchange;
    }

    @Bean
    public Binding lintNewQueueBind(Queue lintNewCommitQueue, DirectExchange lintNewDirectExchange)
    {
        return BindingBuilder.bind(lintNewCommitQueue).to(lintNewDirectExchange).with(ROUTE_DEFECT_COMMIT_LINT_NEW);
    }

    @Bean
    public Queue ccnNewCommitQueue()
    {
        return new Queue(QUEUE_DEFECT_COMMIT_CCN_NEW);
    }

    @Bean
    public DirectExchange ccnNewDirectExchange()
    {
        DirectExchange directExchange = new DirectExchange(EXCHANGE_DEFECT_COMMIT_CCN_NEW);
        directExchange.setDelayed(true);
        return directExchange;
    }

    @Bean
    public Binding ccnNewQueueBind(Queue ccnNewCommitQueue, DirectExchange ccnNewDirectExchange)
    {
        return BindingBuilder.bind(ccnNewCommitQueue).to(ccnNewDirectExchange).with(ROUTE_DEFECT_COMMIT_CCN_NEW);
    }

    @Bean
    public Queue dupcNewCommitQueue()
    {
        return new Queue(QUEUE_DEFECT_COMMIT_DUPC_NEW);
    }

    @Bean
    public DirectExchange dupcNewDirectExchange()
    {
        DirectExchange directExchange = new DirectExchange(EXCHANGE_DEFECT_COMMIT_DUPC_NEW);
        directExchange.setDelayed(true);
        return directExchange;
    }

    @Bean
    public Binding dupcNewQueueBind(Queue dupcNewCommitQueue, DirectExchange dupcNewDirectExchange)
    {
        return BindingBuilder.bind(dupcNewCommitQueue).to(dupcNewDirectExchange).with(ROUTE_DEFECT_COMMIT_DUPC_NEW);
    }

    @Bean
    public Queue clocNewCommitQueue()
    {
        return new Queue(QUEUE_DEFECT_COMMIT_CLOC_NEW);
    }

    @Bean
    public DirectExchange clocNewDirectExchange()
    {
        DirectExchange directExchange = new DirectExchange(EXCHANGE_DEFECT_COMMIT_CLOC_NEW);
        directExchange.setDelayed(true);
        return directExchange;
    }

    @Bean
    public Binding clocNewQueueBind(Queue clocNewCommitQueue, DirectExchange clocNewDirectExchange)
    {
        return BindingBuilder.bind(clocNewCommitQueue).to(clocNewDirectExchange).with(ROUTE_DEFECT_COMMIT_CLOC_NEW);
    }

    @Bean
    public Queue statNewCommitQueue() {
        return new Queue(QUEUE_DEFECT_COMMIT_STAT_NEW);
    }

    @Bean
    public DirectExchange statNewDirectExchange() {
        DirectExchange directExchange = new DirectExchange(EXCHANGE_DEFECT_COMMIT_STAT_NEW);
        directExchange.setDelayed(true);
        return directExchange;
    }

    @Bean
    public Binding statNewQueueBind(Queue statNewCommitQueue, DirectExchange statNewDirectExchange) {
        return BindingBuilder.bind(statNewCommitQueue).to(statNewDirectExchange).with(ROUTE_DEFECT_COMMIT_STAT_NEW);
    }

    @Bean
    public Queue scaNewCommitQueue() {
        return new Queue(QUEUE_DEFECT_COMMIT_SCA_NEW);
    }

    @Bean
    public DirectExchange scaNewDirectExchange() {
        DirectExchange directExchange = new DirectExchange(EXCHANGE_DEFECT_COMMIT_SCA_NEW);
        directExchange.setDelayed(true);
        return directExchange;
    }

    @Bean
    public Binding scaNewQueueBind(Queue scaNewCommitQueue, DirectExchange scaNewDirectExchange) {
        return BindingBuilder.bind(scaNewCommitQueue).to(scaNewDirectExchange).with(ROUTE_DEFECT_COMMIT_SCA_NEW);
    }


    @Bean
    public SimpleMessageListenerContainer lintNewMessageListenerContainer(
            ConnectionFactory connectionFactory,
            LintDefectCommitConsumer lintDefectCommitConsumer,
            Jackson2JsonMessageConverter jackson2JsonMessageConverter) throws NoSuchMethodException
    {
        return messageListenerContainer(connectionFactory, lintDefectCommitConsumer, jackson2JsonMessageConverter,
                CONSUMER_METHOD_NAME, QUEUE_DEFECT_COMMIT_LINT_NEW, CONCURRENT_CONSUMERS, MAX_CONCURRENT_CONSUMERS);
    }

    @Bean
    public SimpleMessageListenerContainer ccnNewMessageListenerContainer(
            ConnectionFactory connectionFactory,
            CCNDefectCommitConsumer ccnDefectCommitConsumer,
            Jackson2JsonMessageConverter jackson2JsonMessageConverter) throws NoSuchMethodException
    {
        return messageListenerContainer(connectionFactory, ccnDefectCommitConsumer, jackson2JsonMessageConverter,
                CONSUMER_METHOD_NAME, QUEUE_DEFECT_COMMIT_CCN_NEW, CONCURRENT_CONSUMERS, MAX_CONCURRENT_CONSUMERS);
    }

    @Bean
    public SimpleMessageListenerContainer dupcNewMessageListenerContainer(
            ConnectionFactory connectionFactory,
            DUPCDefectCommitConsumer dupcDefectCommitConsumer,
            Jackson2JsonMessageConverter jackson2JsonMessageConverter) throws NoSuchMethodException
    {
        return messageListenerContainer(connectionFactory, dupcDefectCommitConsumer, jackson2JsonMessageConverter,
                CONSUMER_METHOD_NAME, QUEUE_DEFECT_COMMIT_DUPC_NEW, CONCURRENT_CONSUMERS, MAX_CONCURRENT_CONSUMERS);
    }

    @Bean
    public SimpleMessageListenerContainer clocNewMessageListenerContainer(
            ConnectionFactory connectionFactory,
            CLOCDefectCommitConsumer clocDefectCommitConsumer,
            Jackson2JsonMessageConverter jackson2JsonMessageConverter) throws NoSuchMethodException
    {
        return messageListenerContainer(connectionFactory, clocDefectCommitConsumer, jackson2JsonMessageConverter,
                CONSUMER_METHOD_NAME, QUEUE_DEFECT_COMMIT_CLOC_NEW, CONCURRENT_CONSUMERS, MAX_CONCURRENT_CONSUMERS);
    }

    @Bean
    public SimpleMessageListenerContainer statNewMessageListenerContainer(
            ConnectionFactory connectionFactory,
            StatDefectCommitConsumer statDefectCommitConsumer,
            Jackson2JsonMessageConverter jackson2JsonMessageConverter) throws NoSuchMethodException {
        return messageListenerContainer(connectionFactory, statDefectCommitConsumer, jackson2JsonMessageConverter,
                CONSUMER_METHOD_NAME, QUEUE_DEFECT_COMMIT_STAT_NEW, CONCURRENT_CONSUMERS, MAX_CONCURRENT_CONSUMERS);
    }

    @Bean
    public SimpleMessageListenerContainer scaNewMessageListenerContainer(
            ConnectionFactory connectionFactory,
            SCADefectCommitConsumer scaDefectCommitConsumer,
            Jackson2JsonMessageConverter jackson2JsonMessageConverter) throws NoSuchMethodException {
        return messageListenerContainer(connectionFactory, scaDefectCommitConsumer, jackson2JsonMessageConverter,
                CONSUMER_METHOD_NAME, QUEUE_DEFECT_COMMIT_SCA_NEW, CONCURRENT_CONSUMERS, MAX_CONCURRENT_CONSUMERS);
    }
}
