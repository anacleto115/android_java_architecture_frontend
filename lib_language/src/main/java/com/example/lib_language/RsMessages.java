package com.example.lib_language;

import java.util.HashMap;

public class RsMessages
{
    private HashMap<String, String> data = null;

    public RsMessages(String language)
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
        this.data.put("lbActive", "Active");
        this.data.put("lbDeleteEntity", "Do you want to delete the selected item?");
        this.data.put("lbExists", "The item is already exist.");
        this.data.put("lbInactive", "Inactive");
        this.data.put("lbLoading", "Loading...");
        this.data.put("lbMessage", "Message");
        this.data.put("lbNoExist", "The information no longer exists.");
        this.data.put("lbSelectItem", "Please, select a item in the list.");
        this.data.put("lbSqlError", "Please, contact to your administrator.");
        this.data.put("lbThisIsInUse", "This item is in used, you cannot delete it.");
        this.data.put("lbTryAgain", "Please, try again.");
        this.data.put("lbMissingInfo", "Please check, there is missing information.");
    }

    private void Es()
    {
        this.data.put("lbActive", "Activo");
        this.data.put("lbDeleteEntity", "¿Desea eliminar el elemento seleccionado?");
        this.data.put("lbExists", "El elemento ya existe.");
        this.data.put("lbInactive", "Inactivo");
        this.data.put("lbLoading", "Cargando...");
        this.data.put("lbMessage", "Mensaje");
        this.data.put("lbNoExist", "La información ya no existe.");
        this.data.put("lbSelectItem", "Por favor, seleccione un elemento de la lista.");
        this.data.put("lbSqlError", "Por favor, póngase en contacto con su administrador.");
        this.data.put("lbThisIsInUse", "Este elemento está en uso, no puede eliminarlo.");
        this.data.put("lbTryAgain", "Por favor, intente de nuevo.");
        this.data.put("lbMissingInfo", "Por favor verifique, falta información.");
    }
}