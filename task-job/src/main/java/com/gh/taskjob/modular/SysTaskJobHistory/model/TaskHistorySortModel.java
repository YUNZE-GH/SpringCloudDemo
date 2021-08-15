package com.gh.taskjob.modular.SysTaskJobHistory.model;

import com.gh.taskjob.modular.SysTaskJobHistory.entity.SysTaskJobHistory;
import lombok.Data;

import java.util.List;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/8/15 14:53
 */
@Data
public class TaskHistorySortModel {

    private String taskId;

    private String taskExecutionDate;

    private Integer total;

    private Integer success;

    private Integer error;

    List<SysTaskJobHistory> historyList;
}
