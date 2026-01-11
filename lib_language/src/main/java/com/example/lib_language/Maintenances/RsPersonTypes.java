package com.example.lib_language.Maintenances;

import java.util.HashMap;

public class RsPersonTypes
{
    private HashMap<String, String> data = null;

    public RsPersonTypes(String language)
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
        this.data.put("clName", "NAME");
        this.data.put("lbName", "Name*");
        this.data.put("lbSubTitle", "Person type");
        this.data.put("lbTitle", "Person types");
    }

    private void Es()
    {
        this.data.put("clName", "NOMBRE");
        this.data.put("lbName", "Nombre*");
        this.data.put("lbSubTitle", "Tipo de persona");
        this.data.put("lbTitle", "Tipos de personas");
    }
}