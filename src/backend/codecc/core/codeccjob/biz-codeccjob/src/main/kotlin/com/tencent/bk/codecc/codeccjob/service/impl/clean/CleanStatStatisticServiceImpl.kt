package com.tencent.bk.codecc.codeccjob.service.impl.clean

import com.tencent.bk.codecc.codeccjob.dao.defect.mongotemplate.StatStatisticDao
import com.tencent.bk.codecc.codeccjob.service.ICleanMongoDataService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CleanStatStatisticServiceImpl @Autowired constructor(
    private val statStatisticDao: StatStatisticDao
) : ICleanMongoDataService {
    override fun clean(projectId: String, taskId: Long, obsoleteBuildIdList: List<String>, taskToolList: List<String>) {
        val start = System.currentTimeMillis()
        statStatisticDao.deleteAllByTaskIdAndBuildIdIn(taskId, obsoleteBuildIdList)
        logger.info("Clean stat statistic: $taskId ${obsoleteBuildIdList.size} ${System.currentTimeMillis() - start}")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(CleanStatStatisticServiceImpl::class.java)
    }
}
