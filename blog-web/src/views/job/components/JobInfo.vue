<template>
    <el-form ref="form" :model="form" label-width="120px" label-suffix=" :" style="padding: 0 20px"
             :disabled="isDisabled">
        <el-row>
            <el-col :span="11">
                <el-form-item label="任务名称" prop="taskName">
                    <el-input v-model="form.taskName" maxlength="30" show-word-limit></el-input>
                </el-form-item>
            </el-col>
            <el-col :span="11" :offset="2">
                <el-form-item label="触发规则" prop="taskPlanType">
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
                    <el-form-item label="Cron通配符" prop="taskPlanCron">
                        <el-input v-model="form.taskPlanCron"></el-input>
                    </el-form-item>
                </div>
                <div v-else>
                    <el-form-item label="执行间隔" prop="taskPlanFixedRate">
                        <el-input v-model="form.taskPlanFixedRate">
                            <template slot="suffix">ms</template>
                        </el-input>
                    </el-form-item>
                </div>
            </el-col>
            <el-col :span="11" :offset="2">
                <el-form-item label="执行方式" prop="taskSequentialExecution">
                    <el-switch v-model="form.taskSequentialExecution" active-value="1" inactive-value="0"
                               active-text="上个任务未执行完再次被触发时，放弃并发执行">
                    </el-switch>
                </el-form-item>
            </el-col>
        </el-row>

        <el-row>
            <el-col :span="24">
                <el-form-item label="自定义参数" prop="taskCustomParameters">
                    <el-input type="textarea" :rows="2" placeholder="请输入json格式数据"
                              v-model="form.taskCustomParameters" maxlength="300" show-word-limit>
                    </el-input>
                </el-form-item>
            </el-col>
        </el-row>

        <el-row>
            <el-col :span="24">
                <el-form-item label="备注" prop="remark">
                    <el-input type="textarea" :rows="2" placeholder="请输入任务备注内容"
                              v-model="form.remark" maxlength="300" show-word-limit>
                    </el-input>
                </el-form-item>
            </el-col>
        </el-row>
    </el-form>
</template>

<script>
export default {
    name: "JobInfo",
    props: ["form", "isDisabled"],
    data() {
        return {

        }
    },
    methods: {
        clearData() {
            this.$nextTick(() => {
                this.$refs['form'] && this.$refs['form'].resetField();
            })
        }
    }
}
</script>

<style scoped>

</style>