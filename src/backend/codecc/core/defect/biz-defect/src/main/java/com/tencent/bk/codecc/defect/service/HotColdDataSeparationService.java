package com.tencent.bk.codecc.defect.service;

public interface HotColdDataSeparationService {

    boolean warmUpColdDataIfNecessary(long taskId);
}