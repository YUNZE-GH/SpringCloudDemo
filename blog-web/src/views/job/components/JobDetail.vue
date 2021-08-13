<template>
    <el-dialog
        :title="title"
        :visible.sync="dialogVisible"
        width="50%"
        append-to-body
        center>

        <div v-loading="loading">
            <el-tabs v-model="activeNameTabs" type="card" @tab-click="handleClick">
                <el-tab-pane label="配置信息" name="first">
                    <el-form ref="form" :model="form" label-width="120px" label-suffix=" :" style="padding: 0 20px"
                             disabled>
                        <JobInfo :form.sync="form"/>
                    </el-form>
                </el-tab-pane>
                <el-tab-pane label="历史日志" name="second">
                    <div style="width: 90%; margin:0 auto;">
                        <el-collapse v-model="activeNameCollapse" accordion>
                            <el-collapse-item title="一致性 Consistency" name="1">
                                <div>与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念；</div>
                                <div>在界面中一致：所有的元素和结构需保持一致，比如：设计样式、图标和文本、元素的位置等。</div>
                            </el-collapse-item>
                            <el-collapse-item title="反馈 Feedback" name="2">
                                <div>控制反馈：通过界面样式和交互动效让用户可以清晰的感知自己的操作；</div>
                                <div>页面反馈：操作后，通过页面元素的变化清晰地展现当前状态。</div>
                            </el-collapse-item>
                            <el-collapse-item title="效率 Efficiency" name="3">
                                <div>简化流程：设计简洁直观的操作流程；</div>
                                <div>清晰明确：语言表达清晰且表意明确，让用户快速理解进而作出决策；</div>
                                <div>帮助用户识别：界面简单直白，让用户快速识别而非回忆，减少用户记忆负担。</div>
                            </el-collapse-item>
                            <el-collapse-item title="可控 Controllability" name="4">
                                <div>用户决策：根据场景可给予用户操作建议或安全提示，但不能代替用户进行决策；</div>
                                <div>结果可控：用户可以自由的进行操作，包括撤销、回退和终止当前操作等。</div>
                            </el-collapse-item>
                        </el-collapse>
                    </div>
                </el-tab-pane>
            </el-tabs>

        </div>

        <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">关 闭</el-button>
        </span>
    </el-dialog>
</template>

<script>

import JobInfo from "@/views/job/components/JobInfo";

import {TASK_JOB_PLAN_DETAIL, TASK_JOB_PLAN_UPDATE} from "@/apis/taskJob"

export default {
    name: "JobEdit",
    components: {JobInfo},
    data() {
        return {
            dialogVisible: false,
            title: "定时任务详情",
            loading: false,
            activeNameTabs: 'first',
            activeNameCollapse: '1',
            form: {
                id: null,
                taskId: null,

                taskName: null,
                taskPlanType: '0',
                taskSequentialExecution: '0',
                taskPlanCron: null,
                taskPlanFixedRate: null,
                taskPlanExecuteClassPath: null,
                taskCustomParameters: null,
                remark: null
            }
        }
    },
    methods: {
        // 打开弹窗页面
        init(id) {
            this.dialogVisible = true;
            this.queryInfo(id);
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
        onUpdate() {
            this.openLoading();
            this.$http.post(TASK_JOB_PLAN_UPDATE, this.form).then(response => {
                let data = response.data;
                if (data.code === 0) {
                    this.$emit("loadInfo");
                    this.$message({
                        message: data.message,
                        type: 'success'
                    });
                } else {
                    this.$message({
                        message: data.message,
                        type: 'error'
                    });
                }
                this.closeLoading();
            })
        },
        handleClick(tab, event) {
            console.log(tab, event);
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

</style>