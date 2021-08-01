package com.gh.taskjob.modular.SysTaskJobPlan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gh.common.enums.CodeEnum;
import com.gh.common.toolsclass.PageFilter;
import com.gh.common.toolsclass.ResultData;
import com.gh.taskjob.modular.SysTaskJobPlan.entity.SysTaskJobPlan;
import com.gh.taskjob.modular.SysTaskJobPlan.mapper.SysTaskJobPlanMapper;
import com.gh.taskjob.modular.SysTaskJobPlan.service.SysTaskJobPlanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 系统-任务作业执行计划表 服务实现类
 * </p>
 *
 * @author gaohan
 * @since 2021-07-31
 */
@Service
public class SysTaskJobPlanServiceImpl extends ServiceImpl<SysTaskJobPlanMapper, SysTaskJobPlan> implements SysTaskJobPlanService {

    @Override
    public ResultData<List<SysTaskJobPlan>> list(PageFilter<SysTaskJobPlan> filter) {
        SysTaskJobPlan bo = filter.getData();
        LambdaQueryWrapper<SysTaskJobPlan> queryWrapper = new LambdaQueryWrapper<>();
        if (bo != null) {
            if (!StringUtils.isEmpty(bo.getTaskPlanType())) {
                queryWrapper.eq(SysTaskJobPlan::getTaskPlanType, bo.getTaskPlanType());
            }
            if (!StringUtils.isEmpty(bo.getTaskName())) {
                queryWrapper.like(SysTaskJobPlan::getTaskName, bo.getTaskName());
            }
            if (!StringUtils.isEmpty(bo.getStatus())) {
                queryWrapper.eq(SysTaskJobPlan::getStatus, bo.getStatus());
            }
            if (!StringUtils.isEmpty(filter.getStartTime())) {
                queryWrapper.gt(SysTaskJobPlan::getCreateTime, filter.getStartTime());
            }
            if (!StringUtils.isEmpty(filter.getEndTime())) {
                queryWrapper.lt(SysTaskJobPlan::getCreateTime, filter.getEndTime());
            }
        }
        queryWrapper.orderByDesc(SysTaskJobPlan::getId);

        if (!StringUtils.isEmpty(filter.getLimit()) && filter.getLimit() == 0) {
            List<SysTaskJobPlan> list = baseMapper.selectList(queryWrapper);
            return new ResultData<>(CodeEnum.SUCCESS.get(), list.size(), list, "查询成功！");
        }

        IPage<SysTaskJobPlan> iPage = new Page<>(filter.getPage(), filter.getLimit());
        iPage = baseMapper.selectPage(iPage, queryWrapper);
        List<SysTaskJobPlan> list = iPage.getRecords();
        return new ResultData<>(CodeEnum.SUCCESS.get(), (int) iPage.getTotal(), list, "查询成功！");
    }

    @Override
    public ResultData<SysTaskJobPlan> add(SysTaskJobPlan bo) {
        bo.setTaskId(UUID.randomUUID().toString());
        bo.setUpdateTime(LocalDateTime.now());
        return null;
    }

    @Override
    public ResultData<SysTaskJobPlan> update(SysTaskJobPlan bo) {
        return null;
    }

    @Override
    public ResultData<SysTaskJobPlan> start(SysTaskJobPlan bo) {
        return null;
    }

    @Override
    public ResultData<SysTaskJobPlan> end(SysTaskJobPlan bo) {
        return null;
    }
}
