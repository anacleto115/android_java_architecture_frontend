package com.example.lib_language;

import java.util.HashMap;

public class RsMenu
{
    private HashMap<String, String> data = null;

    public RsMenu(String language)
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
        this.data.put("lbAccept", "Accept");
        this.data.put("lbAction", "ACTION");
        this.data.put("lbApp", "PersonsApp");
        this.data.put("lbApplications", "Applications");
        this.data.put("lbCancel", "Cancel");
        this.data.put("lbClean", "Clean");
        this.data.put("lbClose", "Close");
        this.data.put("lbEnter", "Enter");
        this.data.put("lbHome", "Home");
        this.data.put("lbMaintenances", "Maintenances");
        this.data.put("lbPersons", "Persons");
        this.data.put("lbPersonTypes", "Person types");

        this.data.put("lbNew", "New");
        this.data.put("lbSave", "Save");
        this.data.put("lbEdit", "Edit");
        this.data.put("lbSelect", "Load");
        this.data.put("lbInsert", "Create");
        this.data.put("lbUpdate", "Modify");
        this.data.put("lbDelete", "Delete");
        this.data.put("lbChoose", "CHOOSE");
        this.data.put("lbCompleted", "Completed");
    }

    private void Es()
    {
        this.data.put("lbAccept", "Aceptar");
        this.data.put("lbAction", "ACCIÓN");
        this.data.put("lbApp", "PersonsApp");
        this.data.put("lbApplications", "Aplicaciones");
        this.data.put("lbCancel", "Cancelar");
        this.data.put("lbClean", "Limpiar");
        this.data.put("lbClose", "Cerrar");
        this.data.put("lbEnter", "Entrar");
        this.data.put("lbHome", "Inicio");
        this.data.put("lbMaintenances", "Mantenimientos");
        this.data.put("lbPersons", "Personas");
        this.data.put("lbPersonTypes", "Tipos de personas");

        this.data.put("lbNew", "Nuevo");
        this.data.put("lbSave", "Guardar");
        this.data.put("lbEdit", "Editar");
        this.data.put("lbSelect", "Cargar");
        this.data.put("lbInsert", "Crear");
        this.data.put("lbUpdate", "Modificar");
        this.data.put("lbDelete", "Borrar");
        this.data.put("lbChoose", "SELECCIONAR");
        this.data.put("lbCompleted", "Completado");
    }
}