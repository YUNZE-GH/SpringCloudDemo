<template>
    <el-dialog
        :title="title"
        :visible.sync="dialogVisible"
        width="50%"
        append-to-body
        center>

        <div v-loading="loading">
            <el-form ref="form" :model="form" label-width="120px" label-suffix=" :" style="padding: 0 20px">
                <el-row>
                    <el-col :span="11">
                        <el-form-item label="任务名称">
                            <el-input v-model="form.taskName" maxlength="30" show-word-limit></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="11" :offset="2">
                        <el-form-item label="执行方式">
                            <el-select v-model="form.taskPlanType" placeholder="请选择">
                                <el-option label="执行一次" value="0"></el-option>
                                <el-option label="无限次" value="1"></el-option>
                                <el-option label="Cron表达式" value="2"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row v-if="form.taskPlanType !== '0'">
                    <el-col :span="11">
                        <div v-if="form.taskPlanType === '2'">
                            <el-form-item label="Cron通配符">
                                <el-input v-model="form.taskPlanCron"></el-input>
                            </el-form-item>
                        </div>
                        <div v-else>
                            <el-form-item label="执行间隔">
                                <el-input v-model="form.taskPlanFixedRate">
                                    <template slot="suffix">ms</template>
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-col>
                    <el-col :span="11" :offset="2">
                        <el-form-item label="执行方式">
                            <el-switch v-model="form.taskSequentialExecution" active-value="1" inactive-value="0"
                                       active-text="上个任务未执行完再次被触发时，放弃并发执行">
                            </el-switch>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :span="24">
                        <el-form-item label="自定义参数">
                            <el-input type="textarea" :rows="2" placeholder="请输入json格式数据"
                                      v-model="form.taskCustomParameters" maxlength="300" show-word-limit>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :span="24">
                        <el-form-item label="备注">
                            <el-input type="textarea" :rows="2" placeholder="请输入任务备注内容"
                                      v-model="form.remark" maxlength="300" show-word-limit>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

            </el-form>
        </div>

        <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="onUpdate()">确 定</el-button>
        </span>
    </el-dialog>
</template>

<script>

import {TASK_JOB_PLAN_DETAIL, TASK_JOB_PLAN_UPDATE} from "@/apis/taskJob"

export default {
    name: "JobEdit",
    data() {
        return {
            dialogVisible: false,
            title: "定时任务编辑",
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