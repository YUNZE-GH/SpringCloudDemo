<template>
    <el-dialog
        :title="title"
        :visible.sync="dialogVisible"
        width="50%"
        append-to-body
        center>

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
                        <el-switch
                            v-model="form.taskSequentialExecution"
                            active-value="1"
                            inactive-value="0"
                            active-color="#13ce66"
                            inactive-color="#ff4949">
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

        <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
    </el-dialog>
</template>

<script>
export default {
    name: "JobEdit",
    data() {
        return {
            dialogVisible: false,
            title: "定时任务编辑",
            form: {
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
        init() {
            this.dialogVisible = true;
        }
    }
}
</script>

<style scoped>
::v-deep .el-input__inner {
    width: 100%;
}

::v-deep .el-select {
    width: 100%;
}
</style>