/*基础服务接口*/
let baseUrl = process.env.VUE_APP_SERVER_PATH_BASE + '/open-interface';

export const AUTH_LOGIN = baseUrl + "/sys-api/auth/login"