package com.yodo1.hadeshelper.service;

import com.yodo1.hadeshelper.constant.ConstantDefine;
import com.yodo1.hadeshelper.struct.ResponseStruct;

import com.yodo1.hadeshelper.utils.Util;
import net.sf.json.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;

import com.yodo1.hadeshelper.constant.ConstantDefine.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YanFeng on 2018/4/19.
 */
public class HadesClient {

    private static final String BODY_KEY_MAIN_RECORD        = "record";
    private static final String BODY_KEY_MAIN_DATA          = "data";

    private static final String BODY_KEY_BASE_STEP_ID       = "step_id";
    private static final String BODY_KEY_BASE_STEP_DES      = "step_des";
    private static final String BODY_KEY_BASE_TYPE_ID       = "type_id";
    private static final String BODY_KEY_BASE_TYPE_DES      = "type_des";
    private static final String BODY_KEY_BASE_TARGET        = "data";
    private static final String BODY_KEY_BASE_ADVICE_SOLVER = "advice_solver";
    private static final String BODY_KEY_BASE_TIMESTAMP     = "time";
    private static final String BODY_KEY_BASE_EXTRA         = "extra";


    private RedisTemplate innerRedisTemplate;
    private Boolean innerAvailable;
    private String moduleName;
    private Map<String,String> stepMapping;
    public static HadesClient CreateRedisClient(RedisTemplate redisTemplate)
    {
        HadesClient newSet = new HadesClient();
        newSet.innerRedisTemplate = redisTemplate;
        newSet.moduleName = "default";
        newSet.stepMapping = new HashMap<>();
        try {
            newSet.innerRedisTemplate.hasKey("testtesttest");
            newSet.innerAvailable = true;
        }
        catch (Exception e)
        {
            newSet.innerAvailable = false;
        }
        return newSet;
    }
    public void SetModuleName(String moduleName)
    {
        this.moduleName = moduleName;
    }
    public void SetStepMapping(Map<String,String> mapping)
    {
        this.stepMapping = mapping;
    }
    public void RecordClientActionStepError(String step_id, String step_des, String type_id, String target, String advice_solver, String extra, String game_appkey, String channel_code, String version, String region_code, String channel_version)
    {
        if(this.isAvailable() && Util.isStrValuable(this.moduleName,step_id,type_id,game_appkey,channel_code,version))
        {
            String msgStr = buildClientActionMessageBodyStr(step_id, step_des, type_id, target, advice_solver, extra, game_appkey, channel_code, version, region_code, channel_version);
        }
    }

    public final boolean isAvailable() {
        return innerAvailable;
    }

    private String buildClientActionMessageBodyStr(String step_id, String step_des, String type_id, String target, String advice_solver, String extra, String game_appkey, String channel_code, String version, String region_code, String channel_version)
    {
        String result = "";
        JSONObject bodyJson = new JSONObject();
        return result;
    }


}
