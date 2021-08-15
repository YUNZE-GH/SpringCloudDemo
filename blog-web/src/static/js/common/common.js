import dayjs from "dayjs";

export default {
    commonHeight() {
        return 'calc(100vh - 60px - 20px)';
    },
    test: function () {
        // alert("公共方法！");
    },
    getBreadcrumbData(route){
        console.log(route)
    },
    /**
     * 将日期转为指定格式
     * @param date 日期
     * @param format 格式（例：YYYY-MM-DD HH:mm:ss）
     * @returns {string}
     */
    format(date, format) {
        if (date == null || date === '') {
            return "";
        }
        try {
            return dayjs(date).format(format);
        } catch (e) {
            return "";
        }
    },
    /**
     * 将日期格式化为YYYY-MM-DD
     * @param date 日期
     */
    formatDate(date) {
        return this.format(date, "YYYY-MM-DD")
    },
    /**
     * 将日期格式化为YYYY-MM-DD HH:mm:ss
     * @param date 日期
     * @returns {string} 2021-01-01 00:00:00
     */
    formatDateTime(date) {
        return this.format(date, "YYYY-MM-DD HH:mm:ss")
    },

}