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
                        <el-radio-group v-model="form.taskPlanType" size="medium">
                            <el-radio label="0">执行一次</el-radio>
                            <el-radio label="1">规则执行</el-radio>
                        </el-radio-group>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row v-if="form.taskPlanType === '1'">
                <el-col :span="11">
                    <el-form-item label="规则类型">
                        <el-radio-group v-model="form.taskPlanTimingMethod" size="medium">
                            <el-radio label="0">cron通配符</el-radio>
                            <el-radio label="1">fixedRate</el-radio>
                        </el-radio-group>
                    </el-form-item>
                </el-col>
                <el-col :span="11" :offset="2">
                    <div v-if="form.taskPlanTimingMethod === '0'">
                        <el-form-item label="cron通配符">
                            <el-input v-model="form.taskPlanCron"></el-input>
                        </el-form-item>
                    </div>
                    <div v-else>
                        <el-form-item label="固定时长">
                            <el-input v-model="form.taskPlanFixedRate">
                                <template slot="suffix">ms</template>
                            </el-input>
                        </el-form-item>
                    </div>
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
                taskPlanTimingMethod: '0',
                taskPlanCron: null,
                taskPlanFixedRate: null,
                taskPlanExecuteClassPath: null,
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
</style>