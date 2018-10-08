package com.yodo1.hadeshelper.struct;

import com.yodo1.hadeshelper.utils.Util;

/**
 * Created by Yanfeng on 2018/4/12.
 */
public class ResponseStruct {
    public enum ResponseCode
    {
        //成功
        SUCCESS(0,"成功"),
        //代码层面
        INIT_ERROR_CLIENT(-1,"客户端初始化错误"),
        INIT_ERROR_SERVER(-2,"服务端初始化错误"),
        //协议层面
        WRONG_PARM(1,"入参类型不正确"),
        WRONG_RESULT(2,"返回值类型不正确"),
        FUNC_NOTFOUND(3,"未找到对应方法"),
        UNZIP_ERROR(4,"原数据解压失败"),
        CHECKSIGN_ERROR(5,"原数据验签失败"),
        DENIAL(10,"拒绝服务"),
        //网络层面
        CLIENT_TIMEOUTWITHSERVER(101,"连接服务器超时"),
        CLIENT_CANNOTCONECTSERVER(100,"无法与服务端建立连接"),
        //未知
        UNKNOWN(9999,"未知错误");
        private int code;
        private String msg;
        private ResponseCode(int code, String msg)
        {
            this.code = code;
            this.msg = msg;
        }

        @Override
        public String toString()
        {
            return "code:" + code +",msg->"+msg;
        }
    }

    public ResponseCode responseCode;
    public long serverProcessDelay;
    public Object customReturnObj;
    public static ResponseStruct WithOutError(Object customReturnObj)
    {
        ResponseStruct rep = new ResponseStruct();
        rep.responseCode = ResponseCode.SUCCESS;
        rep.serverProcessDelay = 0L;
        rep.customReturnObj = customReturnObj;
        return rep;
    }
    public static ResponseStruct WithError(ResponseCode code)
    {
        ResponseStruct rep = new ResponseStruct();
        rep.responseCode = code;
        rep.serverProcessDelay = 0L;
        rep.customReturnObj = null;
        return rep;
    }
    @Override
    public String toString()
    {
        String objStr = customReturnObj==null?"NULL":customReturnObj.toString();
        return "dur:"+serverProcessDelay + Util.LogSplit() + "obj:" + objStr + Util.LogSplit() + responseCode.toString();
    }
}
