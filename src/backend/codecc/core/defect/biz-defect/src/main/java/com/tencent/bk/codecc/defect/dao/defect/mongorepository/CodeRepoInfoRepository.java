package com.tencent.bk.codecc.defect.dao.defect.mongorepository;

import com.tencent.bk.codecc.defect.model.incremental.CodeRepoInfoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 代码仓库信息查询
 *
 * @version V4.0
 * @date 2019/10/16
 */
@Repository
public interface CodeRepoInfoRepository extends MongoRepository<CodeRepoInfoEntity, String> {
    /**
     * 按任务ID查询
     *
     * @param taskId
     * @param buildId
     * @return
     */
    CodeRepoInfoEntity findFirstByTaskIdAndBuildId(long taskId, String buildId);

    /**
     * 按任务ID查询
     *
     * @param taskId
     * @param buildIdSet
     * @return
     */
    List<CodeRepoInfoEntity> findByTaskIdAndBuildIdIn(long taskId, Set<String> buildIdSet);

    /**
     * 按任务ID查询
     *
     * @param taskId
     * @param buildIdSet
     * @return
     */
    List<CodeRepoInfoEntity> findByTaskIdInAndBuildIdIn(List<Long> taskId, List<String> buildIdSet);
}
