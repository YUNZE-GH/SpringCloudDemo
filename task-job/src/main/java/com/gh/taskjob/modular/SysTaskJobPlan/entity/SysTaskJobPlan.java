package com.gh.taskjob.modular.SysTaskJobPlan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统-任务作业执行计划表
 * </p>
 *
 * @author gaohan
 * @since 2021-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysTaskJobPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 执行方式：0-执行一次；1-循环执行
     */
    private Integer taskPlanType;

    /**
     * 计时方法：0-cron；1-fixedRate；
     */
    private Integer taskPlanTimingMethod;

    /**
     * cron通配符
     */
    private String taskPlanCron;

    /**
     * fixedRate间隔时长（单位：毫秒）
     */
    private Integer taskPlanFixedRate;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人主键ID
     */
    private String createUserId;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新人主键ID
     */
    private String updateUserId;

    /**
     * 状态：0-停止；1-启动
     */
    private Integer status;

    /**
     * 是否有效：0-有效；1-无效
     */
    @TableLogic
    private Integer invalid;

    /**
     * 任务执行类路径
     */
    private String taskPlanExecuteClassPath;

    /**
     * 备注
     */
    private String remark;
}
