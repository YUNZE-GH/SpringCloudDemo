<template>
    <div class="search-form-inline">

        <div>
            <el-tag effect="dark" style="margin-bottom: 20px" type="info">总数: {{ total }}</el-tag>
            <el-tag effect="dark" style="margin-left: 20px;margin-bottom: 20px" type="primary">
                成功: {{ success }}
            </el-tag>
            <el-tag effect="dark" style="margin-left: 20px;margin-bottom: 20px" type="danger">失败: {{ error }}</el-tag>
            <el-tag effect="dark" style="margin-left: 20px;margin-bottom: 20px" type="warning">
                其他: {{ (total - success - error) }}
            </el-tag>
        </div>

        <el-collapse v-model="activeName" accordion v-loading="loading" @change="handleChange">
            <el-collapse-item v-for="(item, index) in historyTableSort" :name="item.taskExecutionDate" :key="index">

                <template slot="title">
                    {{ item.taskExecutionDate }}
                    <span style="margin-left: 80px;color: #606266;">
                        <span style="width: 100px;display: inline-block;">总数 <el-badge type="info" :value="item.total"/></span>
                        <span style="width: 100px;display: inline-block;">成功 <el-badge type="primary"
                                                                                       :value="item.success"
                                                                                       v-if="item.success !== 0"/></span>
                        <span style="width: 100px;display: inline-block;">失败 <el-badge type="danger" :value="item.error"
                                                                                       v-if="item.error !== 0"/></span>
                        <span style="width: 100px;display: inline-block;">其他 <el-badge type="warning"
                                                                                       :value="(item.total - item.success - item.error)"
                                                                                       v-if="(item.total - item.success - item.error) !== 0"/></span>
                    </span>
                </template>

                <el-table :data="tableData" height="500" border style="width: 100%; margin: 0 auto" stripe size="mini"
                          v-loading="tableLoading">
                    <el-table-column header-align="center" type="index" label="序号" width="50"></el-table-column>
                    <el-table-column header-align="center" align="center" prop="id" label="主键ID"
                                     width="100"></el-table-column>
                    <el-table-column header-align="center" align="center" prop="status" label="执行状态" width="100">
                        <template slot-scope="scope">
                            <el-tag
                                :type="scope.row.status === 0 ? 'primary' : scope.row.status === 1 ? 'danger' : 'warning'"
                                disable-transitions
                                effect="dark">
                                {{ status[scope.row.status] || '其他' }}
                            </el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column header-align="center" align="center" prop="taskTimeConsuming" label="任务执行时间/S"
                                     min-width="100">
                        <template slot-scope="scope">
                            {{ (scope.row.taskTimeConsuming | 0) / 1000 }}
                        </template>
                    </el-table-column>
                    <el-table-column header-align="center" align="center" prop="taskStartTime" label="任务开始时间"
                                     min-width="180" :formatter="formatter"/>
                    <el-table-column header-align="center" align="center" prop="taskEndTime" label="任务结束时间"
                                     min-width="180" :formatter="formatter"/>
                    <el-table-column header-align="center" align="center" label="操作" width="80">
                        <template slot-scope="scope">
                            <el-button size="mini" @click="handleDetail(scope.$index, scope.row)">详情</el-button>
                        </template>
                    </el-table-column>
                </el-table>

                <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                               :page-sizes="[10, 20, 30, 40, 50]" :page-size="1"
                               layout="total, sizes, prev, pager, next, jumper"
                               :total.sync="count">
                </el-pagination>

            </el-collapse-item>
        </el-collapse>
    </div>
</template>

<script>

import {TASK_JOB_HISTORY_LIST, TASK_JOB_HISTORY_LISTSORT} from '@/apis/taskJob';
import axios from "axios";

export default {
    name: "JobHistoryLog",
    data() {
        return {
            loading: false,
            tableLoading: false,
            activeName: null,
            tableData: [],
            count: 0,
            historyTableSort: [],
            filter: {
                page: 1,
                limit: 10,
                data: {
                    taskId: null,
                    taskExecutionDate: null,
                }
            },
            status: {
                0: "成功",
                1: "失败"
            },
            total: 0,
            success: 0,
            error: 0,
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
            axios.post(TASK_JOB_HISTORY_LISTSORT, this.filter.data)
                .then(response => {
                    let data = response.data;
                    if (data.code === 0) {
                        this.historyTableSort = data.data;
                        this.total = 0;
                        this.success = 0;
                        this.error = 0;
                        this.historyTableSort.forEach(item => {
                            this.total += item.total;
                            this.success += item.success;
                            this.error += item.error;
                        });
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
                });
        },
        handleDetail(index, row) {
            console.log(row.id());
        },
        handleChange() {
            this.openTableLoading();
            this.filter.data.taskExecutionDate = this.activeName;
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
                    this.closeTableLoading();
                })
                .catch(error => {
                    console.log(error);
                    this.closeTableLoading();
                })
        },
        formatter(row, column, cellValue) {
            return this.$common.formatDateTime(cellValue);
        },
        handleSizeChange(val) {
            this.filter.limit = val;
            this.handleChange();
        },
        handleCurrentChange(val) {
            this.filter.page = val;
            this.handleChange();
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
        openTableLoading() {
            this.tableLoading = true;
            setTimeout(() => {
                this.tableLoading = false;
            }, 3000);
        },
        closeTableLoading() {
            setTimeout(() => {
                this.tableLoading = false;
            }, 300);
        },
    }
}
</script>

<style scoped>

</style>