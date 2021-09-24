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
     * 任务执行类映射名
     */
    private String taskPlanExecuteClassName;

    /**
     * 上个任务未执行完再次被触发时，放弃并发执行：0-否；1-是
     */
    private Integer taskSequentialExecution;

    /**
     * 自定义参数(json数据)
     */
    private String taskCustomParameters;

    /**
     * 触发规则：0-执行一次；1-无限次；2-Cron表达式
     */
    private Integer taskPlanType;

    /**
     * cron通配符
     */
    private String taskPlanCron;

    /**
     * fixedRate间隔时长（单位：毫秒）
     */
    private Integer taskPlanFixedRate;

    /**
     * 备注
     */
    private String remark;

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


}
