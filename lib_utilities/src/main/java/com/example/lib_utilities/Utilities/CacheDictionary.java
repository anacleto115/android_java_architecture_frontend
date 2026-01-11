
package com.example.lib_utilities.Utilities;

import java.util.HashMap;

public class CacheDictionary implements ICacheHelper
{
    private HashMap<String, Object> data;

    @Override
    public void Add(String key, Object value)
    {
        Instance();
        data.put(key, value);
    }

    @Override
    public boolean Contains(String key)
    {
        Instance();
        return data.containsKey(key);
    }

    @Override
    public Object Get(String key)
    {
        Instance();
        if (!Contains(key))
            return null;
        return data.get(key);
    }

    @Override
    public void Instance()
    {
        if (data != null)
            return;
        data = new HashMap<String, Object>();
    }

    @Override
    public void Remove(String key)
    {
        Instance();
        if (!Contains(key))
            return;
        data.remove(key);
    }
}