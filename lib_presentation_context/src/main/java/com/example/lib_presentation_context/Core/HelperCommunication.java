
package com.example.lib_presentation_context.Core;

import com.example.lib_communication_context.Core.FactoryCommunicationContext;
import com.example.lib_communication_core.Core.FactoryCommunication;
import com.example.lib_domain_context.ICommunication;
import com.example.lib_domain_context.IHelper;
import com.example.lib_domain_context.MessagesHelper;
import com.example.lib_utilities.Utilities.Delegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelperCommunication<T> extends Helper<T> implements IHelper
{
    protected ICommunication ICommunication;

    @Override
    public HashMap<String, Object> Load(HashMap<String, Object> data) 
    {
        data = super.Load(data);
        FactoryCommunication.IFactoryCommunication = FactoryCommunication.IFactoryCommunication == null ?
            new FactoryCommunicationContext() : FactoryCommunication.IFactoryCommunication;
        if (data.containsKey("ICommunication"))
            ICommunication = (ICommunication)data.get("ICommunication");
        return data;
    }

    @Override
    public HashMap<String, Object> Select(HashMap<String, Object> data) 
    {
        return FuncList(FuncValidate(data), 
            (HashMap<String, Object> d) -> ICommunication.Select(d));
    }

    @Override
    public HashMap<String, Object> Insert(HashMap<String, Object> data) 
    {
        return FuncExec(FuncValidate(data), 
            (HashMap<String, Object> d) -> ICommunication.Insert(d));
    }

    @Override
    public HashMap<String, Object> Update(HashMap<String, Object> data) 
    {
        return FuncExec(FuncValidate(data), 
            (HashMap<String, Object> d) -> ICommunication.Update(d));
    }

    @Override
    public HashMap<String, Object> Delete(HashMap<String, Object> data) 
    {
        return FuncExec(FuncValidate(data), 
            (HashMap<String, Object> d) -> ICommunication.Delete(d));
    }
    
    protected HashMap<String, Object> FuncValidate(HashMap<String, Object> data)
    {
        HashMap<String, Object> response = new HashMap<String, Object>();
        try
        {
            data = Load(data);
            return data;
        }
        catch (Exception ex)
        {
            response = new HashMap<String, Object>();
            response.put("Error", ex.toString());
            return response;
        }
    }
    
    protected HashMap<String, Object> FuncExec(HashMap<String, Object> data, Delegate method)
    {
        HashMap<String, Object> response = new HashMap<String, Object>();
        try
        {
            if (this.IParser != null && 
                data.containsKey("Entity") && 
                !this.IParser.Validate((T)data.get("Entity")))
            {
                response.put("Error", "lbMissingInfo");
                MessagesHelper.Show(
                    ErrorHelper.GetMessage((String)response.get("Error")));
                return response;
            }
            if (IParser != null && 
                data.containsKey("Entity"))
                data.put("Entity", this.IParser.ToDictionary((T)data.get("Entity")));
            response = method.Execute(data);
            if (response.containsKey("Error"))
            {
                MessagesHelper.Show(
                    ErrorHelper.GetMessage((String)response.get("Error")));
                return response;
            }
            if (this.IParser != null && 
                response.containsKey("Entity"))
                response.put("Entity", this.IParser.ToEntity((HashMap<String, Object>)response.get("Entity")));
            return response;
        }
        catch (Exception ex)
        {
            response = new HashMap<String, Object>();
            response.put("Error", ex.toString());
            return response;
        }
    }
    
    protected HashMap<String, Object> FuncList(HashMap<String, Object> data, Delegate method)
    {
        HashMap<String, Object> response = new HashMap<String, Object>();
        try
        {
            response =  method.Execute(data);
            if (response.containsKey("Error"))
            {
                MessagesHelper.Show(
                    ErrorHelper.GetMessage((String)response.get("Error")));
                return response;
            }
            if (this.IParser != null && 
                response.containsKey("Entities"))
            {
                List<T> list = new ArrayList<T>();
                HashMap<String, Object> temp = (HashMap<String, Object>)response.get("Entities");
                for (Map.Entry<String, Object> item : temp.entrySet())
                {
                    HashMap<String, Object> entData = (HashMap<String, Object>)item.getValue();
                    T ent = this.IParser.ToEntity(entData);
                    list.add(ent);
                }
                response.put("Entities", list);
            }
            return response;
        }
        catch (Exception ex)
        {
            response = new HashMap<String, Object>();
            response.put("Error", ex.toString());
            return response;
        }
    }
}