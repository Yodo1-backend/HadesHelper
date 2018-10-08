package com.yodo1.hadeshelper.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YanFeng on 2018/4/10.
 */
public class ConstantDefine {

    public static final String DEFAULT_TYPE_TIMEOUT = "timeout";
    public static final String DEFAULT_TYPE_ACCESS_DENIED = "accessDenied";
    public static final String DEFAULT_TYPE_INTERFACE_NOT_EXIST = "interfaceNotExist";

    public static final String DEFAULT_TYPE_INNER_PART_SERVICE_NOT_AVAILABLE = "innerPartServerNotAvailable";
    public static final String DEFAULT_TYPE_INNER_PART_SERVICE_ERROR = "innerPartServerError";
    public static final String DEFAULT_TYPE_THIRD_PART_SERVICE_NOT_AVAILABLE = "thirdPartServerNotAvailable";
    public static final String DEFAULT_TYPE_THIRD_PART_SERVICE_ERROR = "thirdPartServerError";
    public static final String DEFAULT_TYPE_DB_NOT_AVAILABLE = "dataBaseNotAvailable";
    public static final String DEFAULT_TYPE_DB_ERROR = "dataBaseError";

    public static final String DEFAULT_TYPE_PARAM_WRONG = "paramWrong";
    public static final String DEFAULT_TYPE_PARAM_MISSING = "paramMissing";

    public static final String DEFAULT_ADVICE_SOLVER_OPS = "ops";
    public static final String DEFAULT_ADVICE_SOLVER_SDK_DEV = "sdk-dev";
    public static final String DEFAULT_ADVICE_SOLVER_BACKEND_DEV = "backend-dev";
    public static final String DEFAULT_ADVICE_SOLVER_IT_SUPPORT = "it";
    public static final String DEFAULT_ADVICE_SOLVER_GAME_DEV = "game-dev";
    public static final String DEFAULT_ADVICE_SOLVER_BD = "ops";
    public static final String DEFAULT_ADVICE_SOLVER_QA = "qa";


    public static final Map<String, String> DEFAULT_TYPE_MAPPING = new HashMap<>();
    static
    {
        DEFAULT_TYPE_MAPPING.put(DEFAULT_TYPE_TIMEOUT, "连接超时");
        DEFAULT_TYPE_MAPPING.put(DEFAULT_TYPE_ACCESS_DENIED, "访问被拒绝");
        DEFAULT_TYPE_MAPPING.put(DEFAULT_TYPE_INTERFACE_NOT_EXIST, "接口不存在");

        DEFAULT_TYPE_MAPPING.put(DEFAULT_TYPE_INNER_PART_SERVICE_NOT_AVAILABLE, "内部服务不可用");
        DEFAULT_TYPE_MAPPING.put(DEFAULT_TYPE_INNER_PART_SERVICE_ERROR, "内部服务错误");
        DEFAULT_TYPE_MAPPING.put(DEFAULT_TYPE_THIRD_PART_SERVICE_NOT_AVAILABLE, "外部服务不可用");
        DEFAULT_TYPE_MAPPING.put(DEFAULT_TYPE_THIRD_PART_SERVICE_ERROR, "外部服务错误");
        DEFAULT_TYPE_MAPPING.put(DEFAULT_TYPE_DB_NOT_AVAILABLE, "数据库不可用");
        DEFAULT_TYPE_MAPPING.put(DEFAULT_TYPE_DB_ERROR, "数据库设置错误");

        DEFAULT_TYPE_MAPPING.put(DEFAULT_TYPE_PARAM_WRONG, "参数配置错误");
        DEFAULT_TYPE_MAPPING.put(DEFAULT_TYPE_PARAM_MISSING, "参数配置缺失");
    }
}
