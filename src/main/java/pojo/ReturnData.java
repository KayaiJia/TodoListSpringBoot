package pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/25 09:33
 */
public class ReturnData {
    private ReturnType type;
    private Map<String,Object> data;

    public String getType() {
        return type.name();
    }

    public void setType(ReturnType type) {
        this.type = type;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Object getData(String name){
        return this.data.get(name);
    }

    public void setData(String name, Object obj) {
        if (this.data == null){
            data = new HashMap<>();
        }
        this.data.put(name,obj);
    }
}
