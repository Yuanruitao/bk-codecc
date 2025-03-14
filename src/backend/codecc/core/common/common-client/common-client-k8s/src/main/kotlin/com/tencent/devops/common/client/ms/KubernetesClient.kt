package com.tencent.devops.common.client.ms

import com.tencent.devops.common.client.Client
import com.tencent.devops.common.client.ClientErrorDecoder
import com.tencent.devops.common.client.pojo.AllProperties
import com.tencent.devops.common.service.utils.SpringContextUtil
import com.tencent.devops.common.codecc.util.JsonUtil
import feign.Feign
import feign.Request
import feign.RequestInterceptor
import feign.RetryableException
import feign.Retryer
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.kubernetes.client.discovery.KubernetesInformerDiscoveryClient
import kotlin.reflect.KClass

class KubernetesClient constructor(
    private val discoveryClient: KubernetesInformerDiscoveryClient,
    override val clientErrorDecoder : ClientErrorDecoder,
    override val allProperties: AllProperties
) : Client(
    clientErrorDecoder,
    allProperties,
    JsonUtil.getObjectMapper()
) {

    /**
     * 正常情况下创建feign对象
     */
    override fun <T : Any> get(clz: KClass<T>): T {
        return Feign.builder()
            .client(feignClient)
            .errorDecoder(clientErrorDecoder)
            .encoder(jacksonEncoder)
            .decoder(jacksonDecoder)
            .contract(jaxRsContract)
            .options(Request.Options(10000, 30000))// 10秒连接 30秒收数据
            .requestInterceptor(SpringContextUtil.getBean(RequestInterceptor::class.java,
                "normalRequestInterceptor")) // 获取为feign定义的拦截器
            .target(KubernetesServiceTarget(findServiceName(clz), clz.java, discoveryClient))
    }

    override fun <T : Any> getWithSpecialTag(clz: Class<T>, specialTag: String): T {
        return Feign.builder()
            .client(feignClient)
            .errorDecoder(clientErrorDecoder)
            .encoder(jacksonEncoder)
            .decoder(jacksonDecoder)
            .contract(jaxRsContract)
            .options(Request.Options(10000, 30000))// 10秒连接 30秒收数据
            .requestInterceptor(SpringContextUtil.getBean(RequestInterceptor::class.java,
                "normalRequestInterceptor")) // 获取为feign定义的拦截器
            .target(KubernetesServiceTarget(findServiceName(clz.kotlin), clz, discoveryClient))
    }


    /**
     * 不带任何公共URL前缀构建feign
     */
    override fun <T : Any> getNoneUrlPrefix(clz: Class<T>): T {
        return Feign.builder()
            .client(feignClient)
            .errorDecoder(clientErrorDecoder)
            .encoder(jacksonEncoder)
            .decoder(jacksonDecoder)
            .contract(jaxRsContract)
            .options(Request.Options(10000, 30000))// 10秒连接 30秒收数据
            .target(KubernetesServiceTarget(findServiceName(clz.kotlin), clz, discoveryClient, ""))
    }


    override fun <T : Any> getWithoutRetry(clz: KClass<T>): T {
        return Feign.builder()
            .client(longRunClient)
            .errorDecoder(clientErrorDecoder)
            .encoder(jacksonEncoder)
            .decoder(jacksonDecoder)
            .contract(jaxRsContract)
            .requestInterceptor(SpringContextUtil.getBean(RequestInterceptor::class.java,
                "normalRequestInterceptor")) // 获取为feign定义的拦截器
            .options(Request.Options(10 * 1000, 30 * 60 * 1000))
            .retryer(object : Retryer {
                override fun clone(): Retryer {
                    return this
                }

                override fun continueOrPropagate(e: RetryableException) {
                    throw e
                }
            })
            .target(KubernetesServiceTarget(findServiceName(clz), clz.java, discoveryClient))
    }

    override fun <T : Any> getWithoutRetry(clz: Class<T>): T {
        return getWithoutRetry(clz.kotlin)
    }

    override fun getServiceNodeNum(serviceName: String): Int {
        // namespace隔离
        return discoveryClient.getInstances(getServiceNameWithPrefix(serviceName)).size
    }
}
