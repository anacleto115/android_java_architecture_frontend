package com.example.lib_language.Applications;

import java.util.HashMap;

public class RsLogin
{
    private HashMap<String, String> data = null;

    public RsLogin(String language)
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
        this.data.put("lbPassword", "Password");
        this.data.put("lbTitle", "LOGIN");
        this.data.put("lbUser", "User");
    }

    private void Es()
    {
        this.data.put("lbPassword", "Contraseña");
        this.data.put("lbTitle", "INICIO DE SESIÓN");
        this.data.put("lbUser", "Usuario");
    }
}