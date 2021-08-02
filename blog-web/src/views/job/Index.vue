<template>
    <div class="search-form-inline">
        <el-form ref="filter" :inline="true" :model="filter" label-width="150px">

            <el-row>
                <el-col :span="6">
                    <el-form-item label="任务编号" prop="data.taskId">
                        <el-input v-model="filter.data.taskId" placeholder="请输入任务编号"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item label="任务名称" prop="data.taskName">
                        <el-input v-model="filter.data.taskName" placeholder="请输入任务名称"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item label="任务状态" prop="data.status">
                        <el-select v-model="filter.data.status" placeholder="请选择">
                            <el-option label="停止" value="0"></el-option>
                            <el-option label="启动" value="1"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <div style="float: right">
                        <el-form-item>
                            <el-button type="primary" round @click="loadInfo">查询</el-button>
                            <el-button type="warning" round @click="loadInfo">新增</el-button>
                            <el-button type="info" round @click="resetFilter">重置</el-button>
                        </el-form-item>
                    </div>
                </el-col>
            </el-row>
            <!--            <el-row>
                            <el-col :span="6">
                                <el-form-item label="任务编号" prop="data.taskId">
                                    <el-input v-model="filter.data.taskId" placeholder="请输入任务编号"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="任务名称" prop="data.taskName">
                                    <el-input v-model="filter.data.taskName" placeholder="请输入任务名称"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="任务状态" prop="data.status">
                                    <el-select v-model="filter.data.status" placeholder="请选择">
                                        <el-option label="停止" value="0"></el-option>
                                        <el-option label="启动" value="1"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                        </el-row>-->

        </el-form>


        <div style="background-color: white">
            <el-table
                :data="tableData"
                style="width: 100%" stripe>
                <el-table-column
                    type="index"
                    label="序号"
                    min-width="20" :index="1" header-align="center" align="center">
                </el-table-column>
                <el-table-column
                    prop="taskName"
                    label="任务名称"
                    min-width="180" header-align="center" align="center">
                </el-table-column>
                <el-table-column
                    prop="taskPlanType"
                    label="执行方式"
                    min-width="180" header-align="center" align="center">
                    <template slot-scope="scope">
                        {{ scope.row.taskPlanType === 0 ? "执行一次" : "循环执行" }}
                    </template>
                </el-table-column>
                <el-table-column
                    prop="status"
                    label="任务状态"
                    min-width="180" header-align="center" align="center">
                    <template slot-scope="scope">
                        {{ scope.row.status === 0 ? "停止" : "启动" }}
                    </template>
                </el-table-column>
                <el-table-column
                    prop="createTime"
                    label="创建时间" header-align="center" align="center" width="180">
                </el-table-column>
                <el-table-column header-align="center" align="center" label="操作" min-width="250">
                    <template slot-scope="scope">
                        <el-button
                            size="mini"
                            @click="handleEdit(scope.$index, scope.row)">详情
                        </el-button>
                        <el-button
                            size="mini"
                            type="primary"
                            @click="handleEdit(scope.$index, scope.row)">编辑
                        </el-button>
                        <el-button
                            size="mini"
                            type="success"
                            @click="handleStart(scope.row)"
                            v-if="scope.row.status === 0">启动
                        </el-button>
                        <el-button
                            size="mini"
                            type="warning"
                            @click="handleStop(scope.row)" v-else>停止
                        </el-button>
                        <el-button
                            size="mini"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>

            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :page-sizes="[1, 2, 3, 4]"
                :page-size="1"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>
        </div>

    </div>
</template>

<script>

import {TASK_JOB_PLAN_LIST, TASK_JOB_PLAN_START, TASK_JOB_PLAN_STOP} from "@/apis/taskJob"

export default {
    name: "Index",
    data() {
        return {
            filter: {
                page: 1,
                limit: 10,
                data: {
                    taskId: null,
                    taskName: null,
                    taskPlanType: null,
                    status: null,
                }
            },
            tableData: [],
            total: 0
        }
    },
    methods: {
        loadInfo() {
            this.$http.post(TASK_JOB_PLAN_LIST, this.filter).then(response => {
                let data = response.data;
                if (data.code === 0) {
                    this.tableData = data.data;
                    this.total = data.total;
                    console.log(this.tableData)
                } else {
                    this.$message({
                        message: data.message,
                        type: 'error'
                    });
                }
            })
        },
        handleStart(row) {
            this.$http.post(TASK_JOB_PLAN_START + `${row.id}`, {}).then(response => {
                let data = response.data;
                if (data.code === 0) {
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
            })
        },
        handleEnd(row) {
            this.$http.post(TASK_JOB_PLAN_STOP + `${row.id}`, {}).then(response => {
                let data = response.data;
                if (data.code === 0) {
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
            })
        },
        handleEdit(index, row) {
            console.log(index, row);
        },
        handleDelete(index, row) {
            console.log(index, row);
        },
        resetFilter() {
            this.$refs.filter.resetFields();
        },
        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
        },
        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
        }
    },
    mounted() {
        this.loadInfo();
        console.log(this.$route)
    }
}
</script>

<style scoped>
.search-form-inline {
    padding: 0px 40px;
}

.search-form-inline .el-pagination {
    margin-top: 20px;
    float: right;
}

.search-form-inline .el-table {
    margin-top: 20px;
}

</style>