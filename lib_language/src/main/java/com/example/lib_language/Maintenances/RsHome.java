package com.example.lib_language.Maintenances;

import java.util.HashMap;

public class RsHome
{
    private HashMap<String, String> data = null;

    public RsHome(String language)
    {
        this.data = new HashMap<String, String>();
        this.Load(language);
    }

    public HashMap<String, String> Get()
    {
        return this.data;
    }

    private void Load(String language)
    {
        switch (language)
        {
            case "Es": this.Es(); break;
            default: this.En(); break;
        }
    }

    private void En()
    {
        this.data.put("lbTitle", "Welcome");
    }

    private void Es()
    {
        this.data.put("lbTitle", "Bienvenido");
    }
}