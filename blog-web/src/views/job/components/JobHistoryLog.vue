<template>
    <div>
        <el-collapse v-model="activeName" accordion>
            <el-collapse-item title="2021-08-14" name="1">
                <el-table :data="tableData" height="250" border style="width: 100%" stripe size="mini" v-loading="loading">
                    <el-table-column header-align="center" type="index" label="序号" width="50"></el-table-column>
                    <el-table-column header-align="center" align="center" prop="id" label="主键ID"
                                     width="100"></el-table-column>
                    <el-table-column header-align="center" align="center" prop="status" label="执行状态" width="100">
                        <template slot-scope="scope">
                            <el-tag :type="scope.row.status === 0 ? 'primary' : 'danger'" disable-transitions
                                    effect="dark">
                                {{ status[scope.row.status] }}
                            </el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column header-align="center" align="center" prop="taskEndTime" label="任务执行时间"
                                     min-width="80">
                        {{ '执行时间' }}
                    </el-table-column>
                    <el-table-column header-align="center" align="center" prop="taskStartTime" label="任务开始时间"
                                     min-width="180"></el-table-column>
                    <el-table-column header-align="center" align="center" prop="taskEndTime" label="任务结束时间"
                                     min-width="180"></el-table-column>
                    <el-table-column header-align="center" align="center" label="操作" width="80">
                        <template slot-scope="scope">
                            <el-button size="mini" @click="handleDetail(scope.$index, scope.row)">详情</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-collapse-item>
            <el-collapse-item title="2021-08-13" name="2">
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
</template>

<script>

import {TASK_JOB_HISTORY_LIST} from '@/apis/taskJob';

export default {
    name: "JobHistoryLog",
    data() {
        return {
            loading: false,
            activeName: '1',
            tableData: [],
            count: 0,
            filter: {
                page: 1,
                limit: 10,
                data: {
                    taskId: null,
                }
            },
            status: {
                0: "成功",
                1: "失败"
            },
        }
    },
    methods: {
        loadInfo(taskId) {
            if (!taskId) {
                this.$message({
                    message: "任务编码不能为空！",
                    type: 'error'
                });
                return;
            }
            this.filter.data.taskId = taskId;

            this.queryInfo();
        },
        queryInfo() {
            this.openLoading();
            this.$http.post(TASK_JOB_HISTORY_LIST, this.filter)
                .then(response => {
                    let data = response.data;
                    if (data.code === 0) {
                        this.tableData = data.data;
                        this.count = data.total;
                    } else {
                        this.$message({
                            message: data.message,
                            type: 'error'
                        });
                    }
                    this.closeLoading();
                })
                .catch(error => {
                    console.log(error);
                    this.closeLoading();
                })
        },
        handleDetail(index, row) {
            console.log(row.id());
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