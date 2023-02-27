package com.tencent.bk.codecc.defect.vo;

import com.tencent.devops.common.api.checkerset.CheckerSetVO;
import java.util.ArrayList;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;

public class QueryTaskCheckerSetsResponse extends ArrayList<CheckerSetVO> {

    public QueryTaskCheckerSetsResponse(@NotNull Collection<? extends CheckerSetVO> c) {
        super(c);
    }
}