package com.mul.utils.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @ProjectName: utils
 * @Package: com.mul.utils.log
 * @ClassName: LogExceptionResult
 * @Author: zdd
 * @CreateDate: 2021/4/7 15:51:20
 * @Description: 日志输出管理类
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/7 15:51:20
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LogExceptionResult {
    public static String getException(Exception mE) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        mE.printStackTrace(printWriter);
        Throwable cause = mE.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        return result.toString();
    }
}
