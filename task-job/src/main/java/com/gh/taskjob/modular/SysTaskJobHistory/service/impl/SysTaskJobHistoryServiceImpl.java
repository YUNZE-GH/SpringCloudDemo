package com.gh.taskjob.modular.SysTaskJobHistory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gh.common.toolsclass.PageFilter;
import com.gh.common.toolsclass.ResultData;
import com.gh.taskjob.modular.SysTaskJobHistory.entity.SysTaskJobHistory;
import com.gh.taskjob.modular.SysTaskJobHistory.mapper.SysTaskJobHistoryMapper;
import com.gh.taskjob.modular.SysTaskJobHistory.model.TaskHistorySortModel;
import com.gh.taskjob.modular.SysTaskJobHistory.service.SysTaskJobHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
    public boolean add(SysTaskJobHistory bo) {
        bo.setCreateTime(LocalDateTime.now());
        return baseMapper.insert(bo) > 0;
    }

    @Override
    public ResultData<List<SysTaskJobHistory>> list(PageFilter<SysTaskJobHistory> filter) {
        SysTaskJobHistory bo = filter.getData();
        LambdaQueryWrapper<SysTaskJobHistory> queryWrapper = new LambdaQueryWrapper<>();
        if (bo != null) {
            if (!StringUtils.isEmpty(bo.getTaskId())) {
                queryWrapper.eq(SysTaskJobHistory::getTaskId, bo.getTaskId());
            }
            if (!StringUtils.isEmpty(bo.getTaskExecutionDate())) {
                queryWrapper.eq(SysTaskJobHistory::getTaskExecutionDate, bo.getTaskExecutionDate());
            }
        }
        queryWrapper.orderByDesc(SysTaskJobHistory::getId, SysTaskJobHistory::getCreateTime);
        if (filter.getPage() == 0 && filter.getLimit() == 0) {
            List<SysTaskJobHistory> list = baseMapper.selectList(queryWrapper);
            return ResultData.success(list.size(), list);
        }
        IPage<SysTaskJobHistory> iPage = new Page<>(filter.getPage(), filter.getLimit());
        iPage = baseMapper.selectPage(iPage, queryWrapper);
        return ResultData.success((int) iPage.getTotal(), iPage.getRecords());
    }

    @Override
    public List<TaskHistorySortModel> listSort(SysTaskJobHistory bo) {
        return baseMapper.listSort(bo);
    }
}
