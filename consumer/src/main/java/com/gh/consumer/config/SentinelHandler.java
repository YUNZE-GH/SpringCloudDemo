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
public class SentinelHandler {

    public static ResultData fallbackHandler(BlockException exception) {
        return new ResultData(CodeEnum.OTHER_ERROR.get(), null, "自定义熔断降级控制", SDK.getDateUtils().getDateTime());
    }

    public static ResultData blockHandler(BlockException exception) {
        System.err.println("===========>    自定义限流控制");
        return new ResultData(CodeEnum.OTHER_ERROR.get(), null, "自定义限流控制", SDK.getDateUtils().getDateTime());
    }
}
