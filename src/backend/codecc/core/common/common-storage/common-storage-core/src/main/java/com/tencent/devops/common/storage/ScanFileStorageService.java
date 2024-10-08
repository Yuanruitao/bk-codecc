package com.tencent.devops.common.storage;

import java.io.FileNotFoundException;

/**
 * 扫描过程中间产物存储
 */
public interface ScanFileStorageService {


    /*
     * 简单上传（不分片）
     * @param localFilePath
     * @param subPath
     * @param filename
     * @return
     * @throws FileNotFoundException
     */
    String upload(String localFilePath,String subPath,String filename) throws FileNotFoundException;

    /*
     * Chunk上传三个接口，初始化，上传分片，完成上传
     * @param subPath
     * @param filename
     * @return
     * @throws Exception
     */
    String startChunk(String subPath, String filename) throws Exception;

    Boolean chunkUpload(String localFilePath, String subPath, String filename, Integer chunkNo, String uploadId)
            throws FileNotFoundException;

    String finishChunk(String subPath, String filename, String uploadId) throws FileNotFoundException;

    void download(String localFilePath,String storageType,String urlOrPath);


    String getStorageType();

    /*
     * 判断当前的存储状态是否需要且可以下载
     * NFS 不需要
     * 不对等
     * @param storageType
     * @return
     */
    Boolean ifNeedAndCanDownload(String storageType,String urlOrPath);


    boolean ifNeedLocalMerge(String storageType);
}
