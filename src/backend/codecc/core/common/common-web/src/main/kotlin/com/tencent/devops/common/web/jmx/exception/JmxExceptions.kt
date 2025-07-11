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

package com.tencent.devops.common.web.jmx.exception

import com.tencent.devops.common.service.Profile
import com.tencent.devops.common.service.utils.SpringContextUtil
import org.slf4j.LoggerFactory
import org.springframework.jmx.export.MBeanExporter
import javax.management.ObjectName

object JmxExceptions {

    private val exceptions = HashMap<String/*exceptionName*/, ExceptionBean>()

    fun encounter(exception: Throwable) {
        try {
            val bean = getBean(exception) ?: return
            bean.incre()
        } catch (t: Throwable) {
            logger.warn("Fail to record the exception ${exception.message}", t)
        }
    }

    private fun getBean(t: Throwable): ExceptionBean? {
        val className = t.javaClass.name
        var bean = exceptions[className]
        if (bean == null) {
            synchronized(this) {
                bean = exceptions[className]
                if (bean == null) {
                    bean = ExceptionBean(className)
                    val serviceName = SpringContextUtil.getBean(Profile::class.java).getServiceName()
                    if (serviceName.isNullOrBlank()) {
                        logger.warn("Fail to get the service name, ignore the mbean")
                        return null
                    }
                    val name = "com.tencent.devops.$serviceName:type=exceptions,name=$className"
                    logger.info("Register exception $className mbean")
                    SpringContextUtil.getBean(MBeanExporter::class.java).registerManagedResource(
                        bean!!,
                        ObjectName(name)
                    )
                    exceptions.put(className, bean!!)
                }
            }
        }
        return bean
    }

    private val logger = LoggerFactory.getLogger(JmxExceptions::class.java)
}

fun main(argv: Array<String>) {
    val throwable = Throwable("sss")
    println(throwable.javaClass.name)
}