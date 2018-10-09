package com.yodo1.hadeshelper.service;

import com.yodo1.hadeshelper.constant.ConstantDefine;

import com.yodo1.hadeshelper.utils.Util;
import net.sf.json.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;

import com.yodo1.hadeshelper.constant.ConstantDefine.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    private static final String BODY_KEY_BASE_TARGET        = "target";
    private static final String BODY_KEY_BASE_ADVICE_SOLVER = "advice_solver";
    private static final String BODY_KEY_BASE_TIMESTAMP     = "time";
    private static final String BODY_KEY_BASE_EXTRA         = "extra";

    private static final String BODY_KEY_CLIENT_APPKEY               = "game_appkey";
    private static final String BODY_KEY_CLIENT_CHANNEL              = "channel";
    private static final String BODY_KEY_CLIENT_VERSION              = "version";
    private static final String BODY_KEY_CLIENT_REGION_CODE          = "region_code";
    private static final String BODY_KEY_CLIENT_CHANNEL_VERSION      = "channel_version";

    private RedisTemplate innerRedisTemplate;
    private String redisMQKey;

    private Boolean innerAvailable;
    private String moduleName;
    private Map<String,String> stepMapping;
    private Map<String,String> typeMapping;
    private HadesClient(){}
    public static HadesClient CreateRedisClient(RedisTemplate redisTemplate, String mqKey)
    {
        if(null == redisTemplate || "".equals(mqKey))
        {
            return null;
        }
        HadesClient newSet = new HadesClient();
        newSet.innerRedisTemplate = redisTemplate;
        newSet.redisMQKey = mqKey;
        newSet.moduleName = "default";
        newSet.stepMapping = new HashMap<>();
        newSet.typeMapping = ConstantDefine.DEFAULT_TYPE_MAPPING;
        try {
            newSet.innerRedisTemplate.hasKey(newSet.redisMQKey);
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
    public void AddStepMapping(Map<String,String> mapping)
    {
        this.stepMapping.putAll(mapping);
    }
    public void AddTypeMapping(Map<String,String> mapping)
    {
        this.typeMapping.putAll(mapping);
    }
    public void RecordClientActionStepError(String step_id, String type_id, String target, String advice_solver, String extra, String game_appkey, String channel_code, String version, String region_code, String channel_version)
    {
        if(this.isAvailable() && Util.isStrValuable(this.moduleName,step_id,type_id,game_appkey,channel_code,version))
        {
            String msgStr = buildClientActionMessageBodyStr(step_id, type_id, target, advice_solver, extra, game_appkey, channel_code, version, region_code, channel_version);
            pushMessage(msgStr);
        }
    }
    public void RecordActionStepError(String step_id, String type_id, String target, String advice_solver, String extra, Map<String,String> customInfoPair)
    {
        if(this.isAvailable() && Util.isStrValuable(this.moduleName,step_id,type_id))
        {
            String msgStr = buildCustomActionMessageBodyStr(step_id, type_id, target, advice_solver, extra, customInfoPair);
            pushMessage(msgStr);
        }
    }
    public final boolean isAvailable() {
        return innerAvailable;
    }

    private String buildClientActionMessageBodyStr(String step_id, String type_id, String target, String advice_solver, String extra, String game_appkey, String channel_code, String version, String region_code, String channel_version)
    {
        JSONObject bodyJson = new JSONObject();
        bodyJson.put(BODY_KEY_MAIN_RECORD,this.moduleName);
        JSONObject dataJson = getBaseDataJsonObj(step_id, type_id, target, advice_solver, extra);
        dataJson.putIfAbsent(BODY_KEY_CLIENT_APPKEY,game_appkey);
        dataJson.putIfAbsent(BODY_KEY_CLIENT_CHANNEL,channel_code);
        dataJson.putIfAbsent(BODY_KEY_CLIENT_VERSION,version);
        dataJson.putIfAbsent(BODY_KEY_CLIENT_REGION_CODE,region_code);
        dataJson.putIfAbsent(BODY_KEY_CLIENT_CHANNEL_VERSION,channel_version);

        bodyJson.put(BODY_KEY_MAIN_DATA,dataJson);
        return bodyJson.toString();
    }
    private String buildCustomActionMessageBodyStr(String step_id, String type_id, String target, String advice_solver, String extra, Map<String,String> customPair)
    {
        JSONObject bodyJson = new JSONObject();
        bodyJson.put(BODY_KEY_MAIN_RECORD,this.moduleName);
        JSONObject dataJson = getBaseDataJsonObj(step_id, type_id, target, advice_solver, extra);
        if(null != customPair && customPair.size() > 0)
        {
            Set<String> keySet = customPair.keySet();
            for(String currentEntryKey : keySet)
            {
                //will not overwrite base key
                dataJson.putIfAbsent(currentEntryKey,customPair.get(currentEntryKey));
            }
        }
        bodyJson.put(BODY_KEY_MAIN_DATA,dataJson);
        return bodyJson.toString();
    }
    private JSONObject getBaseDataJsonObj(String step_id, String type_id, String target, String advice_solver, String extra)
    {
        JSONObject result = new JSONObject();
        result.put(BODY_KEY_BASE_STEP_ID,step_id);
        result.put(BODY_KEY_BASE_STEP_DES,this.stepMapping.getOrDefault(step_id,step_id));
        result.put(BODY_KEY_BASE_TYPE_ID,type_id);
        result.put(BODY_KEY_BASE_TYPE_DES,this.typeMapping.getOrDefault(type_id,type_id));
        result.put(BODY_KEY_BASE_ADVICE_SOLVER,advice_solver);
        result.put(BODY_KEY_BASE_EXTRA,extra);
        result.put(BODY_KEY_BASE_TARGET,target);
        result.put(BODY_KEY_BASE_TIMESTAMP,Util.GetCurrentTimeStamp());
        return result;
    }
    private void pushMessage(String message)
    {
        if(isAvailable())
        {
            this.innerRedisTemplate.opsForList().leftPush(this.redisMQKey,message);
        }
    }
}
