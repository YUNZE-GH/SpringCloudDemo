package com.gh.consumer.config;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.gh.common.SDK;
import com.gh.common.enums.CodeEnum;
import com.gh.common.toolsclass.ResultData;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/1/31 15:31
 */
public class SentinelBlockHandler {

    public static ResultData fuseHandler(String id, BlockException exception) throws Exception {
        return new ResultData(CodeEnum.OTHER_ERROR.get(), null, id + "自定义熔断降级控制", SDK.getDateUtils().getDateTime());
    }

    public static ResultData flowHandler(BlockException exception) throws Exception {
        return new ResultData(CodeEnum.OTHER_ERROR.get(), null, "自定义流控保护", SDK.getDateUtils().getDateTime());
    }

}
