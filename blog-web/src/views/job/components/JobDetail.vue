<template>
    <el-drawer :title="title" :visible.sync="dialogVisible" size="50%" append-to-body center>

        <el-container>
            <el-main v-loading="loading">
                <el-tabs v-model="activeNameTabs" type="card" @tab-click="handleClick">
                    <el-tab-pane name="first">
                        <span slot="label"><i class="el-icon-setting"></i> 配置信息</span>
                        <JobInfo :form.sync="form" :is-disabled="true"/>
                    </el-tab-pane>
                    <el-tab-pane name="second">
                        <span slot="label"><i class="el-icon-time"></i> 历史日志</span>
                        <div style="width: 90%; margin:0 auto;">
                            <JobHistoryLog ref="job_history_log"/>
                        </div>
                    </el-tab-pane>
                </el-tabs>
            </el-main>

            <el-footer height="80px" style="text-align: center;line-height: 80px">
                <el-button @click="dialogVisible = false">关 闭</el-button>
            </el-footer>
        </el-container>
    </el-drawer>
</template>

<script>

import JobInfo from "@/views/job/components/JobInfo";
import JobHistoryLog from "@/views/job/components/JobHistoryLog";

import {TASK_JOB_PLAN_DETAIL} from "@/apis/taskJob"

export default {
    name: "JobEdit",
    components: {JobInfo, JobHistoryLog},
    data() {
        return {
            dialogVisible: false,
            title: "定时任务详情",
            loading: false,
            activeNameTabs: 'first',
            id: null,
            form: {
                id: null,
                taskId: null,

                taskName: null,
                taskPlanType: '0',
                taskSequentialExecution: '0',
                taskPlanCron: null,
                taskPlanFixedRate: null,
                taskPlanExecuteClassName: null,
                taskCustomParameters: null,
                remark: null
            }
        }
    },
    methods: {
        // 打开弹窗页面
        init(id) {
            this.dialogVisible = true;
            this.id = id;
            this.$nextTick(() => {
                this.handleClick()
            })
        },
        // 查询任务计划详细信息
        queryInfo(id) {
            this.openLoading();
            this.$http.get(TASK_JOB_PLAN_DETAIL + `${id}`, {}).then(response => {
                let data = response.data;
                if (data.code === 0) {
                    for (const fKey in this.form) {
                        if (data.data[fKey]) {
                            this.form[fKey] = data.data[fKey] + "";
                        }
                    }
                } else {
                    this.$message({
                        message: data.message,
                        type: 'error'
                    });
                }
                this.closeLoading();
            })
        },
        handleClick() {
            if (this.activeNameTabs === 'second') {
                this.$refs.job_history_log.loadInfo(this.form.taskId);
            } else {
                this.queryInfo(this.id)
            }
        },
        openLoading() {
            this.loading = true;
            setTimeout(() => {
                this.loading = false;
            }, 3000);
        },
        closeLoading() {
            setTimeout(() => {
                this.loading = false;
            }, 300);
        }

    }
}
</script>

<style scoped>
.el-container {
    height: calc(100vh - 80px);
}

.el-main {
    /*height: calc(1vh - 800px);*/
}
</style>