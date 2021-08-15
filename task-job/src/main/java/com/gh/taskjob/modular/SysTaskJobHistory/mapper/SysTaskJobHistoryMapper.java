package com.gh.taskjob.modular.SysTaskJobHistory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gh.taskjob.modular.SysTaskJobHistory.entity.SysTaskJobHistory;
import com.gh.taskjob.modular.SysTaskJobHistory.model.TaskHistorySortModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统-任务作业执行历史表 Mapper 接口
 * </p>
 *
 * @author gaohan
 * @since 2021-07-31
 */
public interface SysTaskJobHistoryMapper extends BaseMapper<SysTaskJobHistory> {
    List<TaskHistorySortModel> listSort(@Param("bo") SysTaskJobHistory bo);
}
