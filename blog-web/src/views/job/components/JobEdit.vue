<template>
    <el-drawer :title="title + typeCode[type] " :visible.sync="dialogVisible" size="50%" append-to-body center>

        <el-container>
            <el-main v-loading="loading">
                <JobInfo ref="job_info" :form.sync="form"/>
            </el-main>

            <el-footer height="80px" style="text-align: center;line-height: 80px">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="submit()">确 定</el-button>
            </el-footer>
        </el-container>
    </el-drawer>
</template>

<script>

import JobInfo from "@/views/job/components/JobInfo";

import {TASK_JOB_PLAN_DETAIL, TASK_JOB_PLAN_UPDATE, TASK_JOB_PLAN_ADD} from "@/apis/taskJob"

export default {
    name: "JobEdit",
    components: {JobInfo},
    data() {
        return {
            dialogVisible: false,
            title: "定时任务",
            loading: false,
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
            },
            typeCode: {
                'add': "新增",
                'edit': "编辑"
            },
            type: null,
        }
    },
    methods: {
        // 打开弹窗页面
        init(type, id) {
            this.dialogVisible = true;
            this.type = type;

            this.clearData();
            this.$nextTick(()=>{
                if (type === 'edit') {
                    this.queryInfo(id);
                }
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
        submit() {
            if (this.type === 'edit') {
                this.onUpdate();
            } else {
                this.onCreate();
            }
        },
        onCreate() {
            this.openLoading();
            this.$http.post(TASK_JOB_PLAN_ADD, this.form).then(response => {
                let data = response.data;
                if (data.code === 0) {
                    this.$emit("loadInfo");
                    this.dialogVisible = false;
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
        },
        clearData() {
            this.form = {
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
    }
}
</script>

<style scoped>

</style>