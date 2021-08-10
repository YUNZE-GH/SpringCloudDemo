package com.gh.taskjob.modular.SysTaskJobHistory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gh.taskjob.modular.SysTaskJobHistory.entity.SysTaskJobHistory;

/**
 * <p>
 * 系统-任务作业执行历史表 服务类
 * </p>
 *
 * @author gaohan
 * @since 2021-07-31
 */
public interface SysTaskJobHistoryService extends IService<SysTaskJobHistory> {

    boolean add(SysTaskJobHistory bo);
}
