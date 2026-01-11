package com.example.lib_language.Applications;

import java.util.HashMap;

public class RsPersons
{
    private HashMap<String, String> data = null;

    public RsPersons(String language)
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
        this.data.put("clBorn", "BORN");
        this.data.put("clFile", "FILE");
        this.data.put("clName", "NAME");
        this.data.put("clSSN", "SSN");
        this.data.put("clState", "STATE");
        this.data.put("clType", "TYPE");
        this.data.put("lbBorn", "Born*");
        this.data.put("lbFile", "File");
        this.data.put("lbName", "Name*");
        this.data.put("lbSSN", "SSN*");
        this.data.put("lbState", "State");
        this.data.put("lbSubTitle", "Person");
        this.data.put("lbTitle", "Persons");
        this.data.put("lbType", "Type*");
    }

    private void Es()
    {
        this.data.put("lbTitle", "Bienvenido");
        this.data.put("clBorn", "FECHA N.");
        this.data.put("clFile", "ARCHIVO");
        this.data.put("clName", "NOMBRE");
        this.data.put("clSSN", "CEDULA");
        this.data.put("clState", "ESTADO");
        this.data.put("clType", "TIPO");
        this.data.put("lbBorn", "Fecha n*");
        this.data.put("lbFile", "Archivo");
        this.data.put("lbName", "Nombre*");
        this.data.put("lbSSN", "Cedula*");
        this.data.put("lbState", "Estado");
        this.data.put("lbSubTitle", "Persona");
        this.data.put("lbTitle", "Personas");
        this.data.put("lbType", "Tipo*");
    }
}