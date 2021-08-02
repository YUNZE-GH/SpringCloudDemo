package com.gh.taskjob.modular.SysTaskJobHistory.service.impl;

import com.gh.common.toolsclass.ResultData;
import com.gh.taskjob.modular.SysTaskJobHistory.entity.SysTaskJobHistory;
import com.gh.taskjob.modular.SysTaskJobHistory.mapper.SysTaskJobHistoryMapper;
import com.gh.taskjob.modular.SysTaskJobHistory.service.SysTaskJobHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统-任务作业执行历史表 服务实现类
 * </p>
 *
 * @author gaohan
 * @since 2021-07-31
 */
@Service
public class SysTaskJobHistoryServiceImpl extends ServiceImpl<SysTaskJobHistoryMapper, SysTaskJobHistory> implements SysTaskJobHistoryService {

    @Override
    public ResultData<SysTaskJobHistory> add(SysTaskJobHistory bo) {
        bo.setCreateTime(LocalDateTime.now());
        baseMapper.insert(bo);
        return null;
    }
}
