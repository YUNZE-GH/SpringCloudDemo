/*定时任务服务接口*/
// let baseUrl = '/scheduled';
let baseUrl = process.env.VUE_APP_SERVER_PATH_TASK_JOB;

export const TASK_JOB_PLAN_LIST = baseUrl + "/taskJob/sysTaskJobPlan/list"
export const TASK_JOB_PLAN_DETAIL = baseUrl + "/taskJob/sysTaskJobPlan/detail/"
export const TASK_JOB_PLAN_ADD = baseUrl + "/taskJob/sysTaskJobPlan/add"
export const TASK_JOB_PLAN_UPDATE = baseUrl + "/taskJob/sysTaskJobPlan/update"
export const TASK_JOB_PLAN_DELETE = baseUrl + "/taskJob/sysTaskJobPlan/delete/"
export const TASK_JOB_PLAN_START = baseUrl + "/taskJob/sysTaskJobPlan/start/"
export const TASK_JOB_PLAN_STOP = baseUrl + "/taskJob/sysTaskJobPlan/stop/"

export const TASK_JOB_HISTORY_LIST = baseUrl + "/taskJob/sysTaskJobHistory/list"
export const TASK_JOB_HISTORY_LISTSORT = baseUrl + "/taskJob/sysTaskJobHistory/listSort"


