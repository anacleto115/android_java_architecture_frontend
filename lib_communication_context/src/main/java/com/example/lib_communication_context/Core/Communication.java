
package com.example.lib_communication_context.Core;

import java.util.HashMap;
import com.example.lib_domain_context.FactoryCaller;
import com.example.lib_domain_context.ICaller;
import com.example.lib_domain_context.ICommunication;

public class Communication implements ICommunication
{
    public ICaller client;
    public String Name = "",
                  Service = "",
                  End = "";
    public static String Protocol = "http://",
                  Base = "192.168.0.35",
                  CreateBy = "Communication";
        
    public HashMap<String, Object> Load(HashMap<String, Object> data)
    {
        client = client == null ? FactoryCaller.Get(data) : client;
        return data;
    }
    
    protected HashMap<String, Object> BuildUrl(HashMap<String, Object> data, String Method) 
    {
        data.put("Url", Protocol + Base + "/" + Service + "/" + Name + "/" + Method + End);
        data.put("UrlToken", Protocol + Base + "/" + Service + "/Token/Authenticate" + End);
        if (!data.containsKey("create_by"))
            data.put("create_by", CreateBy);
        return data;
    }

    @Override
    public HashMap<String, Object> Select(HashMap<String, Object> data) 
    {
        data = Load(data);
        data = BuildUrl(data, "Select");
        return client.Execute(data);
    }

    @Override
    public HashMap<String, Object> Insert(HashMap<String, Object> data) 
    {
        data = Load(data);
        data = BuildUrl(data, "Insert");
        return client.Execute(data);
    }

    @Override
    public HashMap<String, Object> Update(HashMap<String, Object> data) 
    {
        data = Load(data);
        data = BuildUrl(data, "Update");
        return client.Execute(data);
    }

    @Override
    public HashMap<String, Object> Delete(HashMap<String, Object> data) 
    {
        data = Load(data);
        data = BuildUrl(data, "Delete");
        return client.Execute(data);
    }        
}