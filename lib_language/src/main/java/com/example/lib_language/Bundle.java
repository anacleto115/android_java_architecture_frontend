package com.example.lib_language;

import java.util.HashMap;
import com.example.lib_language.Maintenances.*;
import com.example.lib_language.Applications.*;

public class Bundle
{
    private static HashMap<String, Object> data = null;

    public static void Load(String language)
    {
        Bundle.data = Bundle.data == null ?
            new HashMap<String, Object>() : Bundle.data;
        Bundle.data.put("RsMenu", (new RsMenu(language)).Get());
        Bundle.data.put("RsMessages", (new RsMessages(language)).Get());
        Bundle.data.put("RsHome", (new RsHome(language)).Get());
        Bundle.data.put("RsPersonTypes", (new RsPersonTypes(language)).Get());
        Bundle.data.put("RsLogin", (new RsLogin(language)).Get());
        Bundle.data.put("RsPersons", (new RsPersons(language)).Get());
    }

    public static String Get(String resource, String key)
    {
        String response = "";
        if (!Bundle.data.containsKey(resource))
            return response;
        HashMap<String, String> resources = (HashMap<String, String>)Bundle.data.get(resource);
        if (!resources.containsKey(key))
            return response;
        return resources.get(key);
    }
}