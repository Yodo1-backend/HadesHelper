package com.yodo1.hadeshelper.struct;

/**
 * Created by YanFeng on 2018/4/10.
 */
public enum ResultCode {
    //操作结果
    RESPONSE_SUCCESS(0,""),
    RESPONSE_MISSING_PARM(1,"缺少必要参数"),
    RESPONSE_SIGN_INVALID(2,"sign值验证失败"),
    RESPONSE_SERVER_ERROR(3,"服务器异常"),
    RESPONSE_TARGET_EXIST(4,"目标已存在"),
    RESPONSE_TARGET_NOT_EXIST(5,"目标不存在"),
    RESPONSE_PARA_TOOLONG(6,"参数长度超长"),
    RESPONSE_FORMAT_ERROR(7,"请求体格式错误"),
    RESPONSE_LETEST(10,"本地参数已是最新，无需更新"),
    RESPONSE_CONFIG_ERROR(11,"配置错误"),
    RESPONSE_TRANS_ERROR(12,"目标已存在,添加失败"),

    RESPONSE_UNINIT(100,"尚未初始化"),
    RESPONSE_IN_PARAM_ERROR(101,"入参错误"),
    RESPONSE_SERVER_ERROR_PERMISSION_DENIED(1000,"服务端启动失败-权限不足"),
    RESPONSE_SERVER_ERROR_PORTEXIST(1001,"服务端启动失败-端口被占用"),
    RESPONSE_SERVER_ERROR_UNSUPPORT(1002,"服务端启动失败-不支持"),

    RESPONSE_CLIENT_ERROR_CANNOTCONNECTTOSERVER(3000,"无法与服务端建立连接");
    private int code;
    private String msg;

    private ResultCode(int code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }
    public String GetCode()
    {
        return this.code + "";
    }
    @Override
    public String toString()
    {
        return String.format("{\"error_code\":\"%s\",\"error\":\"%s\"}",code,msg);
    }
}
