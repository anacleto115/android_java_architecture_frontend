
package com.example.lib_utilities.Utilities;

public class CacheHelper 
{
    private static ICacheHelper ICacheHelper;

    public static void Add(String key, Object value)
    {
        CreateInstance();
        ICacheHelper.Add(key, value);
    }

    public static void CreateInstance()
    {
        CreateInstance(null);
    }
    
    public static void CreateInstance(ICacheHelper iCacheHelper)
    {
        if (ICacheHelper != null)
            return;
        if (iCacheHelper != null)
            ICacheHelper = iCacheHelper;
        else if (ICacheHelper == null)
            ICacheHelper = new CacheDictionary();
    }

    public static boolean Contains(String key)
    {
        CreateInstance();
        return ICacheHelper.Contains(key);
    }

    public static Object Get(String key)
    {
        CreateInstance();
        return ICacheHelper.Get(key);
    }

    public static void Remove(String key)
    {
        CreateInstance();
        ICacheHelper.Remove(key);
    }
}