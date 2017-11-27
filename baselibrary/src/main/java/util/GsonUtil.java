package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * GSON Util
 * @author whq
 *
 */
public class GsonUtil {

    private static Gson gson=new Gson();
    public static Gson getInstance(){
        return gson;
    }

    public static String toJson(Object obj){
       return gson.toJson(obj);
    }


    public static <T> T fromJson(String json, Class<T> tClass){
        try{
            return gson.fromJson(json,tClass);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> fromJson(String json, TypeToken<List<T>> typeToken){
        try{
            return gson.fromJson(json,typeToken.getType());
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
