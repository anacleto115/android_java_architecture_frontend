
package com.example.lib_utilities.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonHelper
{
    private static JSONObject parser = new JSONObject();
    
    public static String ConvertToString(HashMap<String,Object> data)
    {
        JSONObject jsonObject = new JSONObject(data);
        return jsonObject.toString().replace('"', '\"');
    }
    
    public static HashMap<String,Object> ConvertToObject(String data) throws Exception
    {
        JSONObject jsonObject = new JSONObject(data);
        return (HashMap<String,Object>)ToDictionary(jsonObject);
    }

    private static HashMap<String, Object> ToDictionary(JSONObject object) throws Exception
    {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext())
        {
            String key = keysItr.next();
            Object value = object.get(key);

            if (value instanceof JSONArray)
                value = ToList((JSONArray) value);
            else if (value instanceof JSONObject)
                value = ToDictionary((JSONObject) value);
            map.put(key, value);
        }
        return map;
    }

    private static List<Object> ToList(JSONArray array) throws Exception
    {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++)
        {
            Object value = array.get(i);
            if (value instanceof JSONArray)
                value = ToList((JSONArray) value);
            else if (value instanceof JSONObject)
                value = ToDictionary((JSONObject) value);
            list.add(value);
        }
        return list;
    }
}