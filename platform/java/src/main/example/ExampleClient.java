import com.yodo1.hadeshelper.service.HadesClient;
import com.yodo1.hadeshelper.struct.ResponseStruct;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by YanFeng on 2018/4/23.
 */
public class ExampleClient {
    public String SayHello(String name,int age)
    {
        return "Hello!"+name+",you are "+age +" now!";
    }

    public static void main(String... args)
    {
        System.out.println("开始模拟Client");
    }
}
