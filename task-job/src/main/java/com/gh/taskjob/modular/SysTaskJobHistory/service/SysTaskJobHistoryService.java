package com.gh.taskjob.modular.SysTaskJobHistory.service;

import com.gh.common.toolsclass.ResultData;
import com.gh.taskjob.modular.SysTaskJobHistory.entity.SysTaskJobHistory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统-任务作业执行历史表 服务类
 * </p>
 *
 * @author gaohan
 * @since 2021-07-31
 */
public interface SysTaskJobHistoryService extends IService<SysTaskJobHistory> {

    ResultData<SysTaskJobHistory> add(SysTaskJobHistory bo);
}
