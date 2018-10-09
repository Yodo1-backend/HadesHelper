import com.yodo1.hadeshelper.service.HadesClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.yodo1.hadeshelper.constant.ConstantDefine;

import java.util.*;

/**
 * Created by YanFeng on 2018/4/23.
 */
public class ExampleClient {
    public String SayHello(String name,int age)
    {
        return "Hello!"+name+",you are "+age +" now!";
    }

    public static JedisConnectionFactory connectionFactory() {

        JedisConnectionFactory thisFactory = new JedisConnectionFactory();
        thisFactory.setHostName("192.168.1.132");
        thisFactory.setPort(6379);
        thisFactory.setPassword("yodo1pwd");
        thisFactory.afterPropertiesSet();
        return thisFactory;
    }
    public static StringRedisTemplate redisTemplate() {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(connectionFactory());
        return stringRedisTemplate;
    }
    public static void main(String... args)
    {
        StringRedisTemplate redisTemplate = redisTemplate();
        HadesClient client = HadesClient.CreateRedisClient(redisTemplate,"Hades-Record");
        client.SetModuleName("ucap");
        Map<String,String> stepMapping = new HashMap<>();
        stepMapping.put("init","初始化");
        stepMapping.put("recordInDB","写入数据库");
        stepMapping.put("test","测试");
        client.AddStepMapping(stepMapping);
        for(int i =0;i<1000;i++)
        {
            client.RecordClientActionStepError(
                    "init",
                    ConstantDefine.DEFAULT_TYPE_ACCESS_DENIED,
                    "aaaa",
                    ConstantDefine.DEFAULT_ADVICE_SOLVER_BACKEND_DEV,
                    "eeeee",
                    "testgame",
                    "testchannel",
                    "testversion",
                    "testregion",
                    "2");
        }

        System.out.println("开始模拟Client");
    }
}
