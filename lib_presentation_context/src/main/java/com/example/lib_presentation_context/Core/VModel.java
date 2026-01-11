
package com.example.lib_presentation_context.Core;

import com.example.lib_domain_context.Enumerables;
import com.example.lib_domain_context.Enumerables.Action;
import com.example.lib_domain_context.Enumerables.Loading;
import com.example.lib_domain_context.IEntities;
import com.example.lib_domain_context.IHelper;
import com.example.lib_domain_context.IScreen;
import com.example.lib_domain_context.IViewModel;
import com.example.lib_domain_context.LogHelper;
import com.example.lib_domain_context.MessagesHelper;
import com.example.lib_domain_context.MessagesHelper.Message;
import com.example.lib_language.Bundle;
import com.example.lib_presentation_core.Core.FactoryHelper;
import com.example.lib_utilities.Utilities.JLinq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class VModel<T> implements IViewModel<T>
{
    protected T current, currentCopy;
    protected List<T> list, copy;
    protected IScreen IScreen;
    protected IHelper IHelper;
    protected Enumerables.Architecture architecture = Enumerables.Architecture.Services;
    protected boolean isLoading = false;
    protected int fontSize = 16;
    protected String lbSelectItem, lbDeleteEntity;

    public VModel(HashMap<String, Object> data)
    {
        try
        {
            if (data.containsKey("Architecture"))
            {
                architecture = (Enumerables.Architecture)data.get("Architecture");
                data.remove("Architecture");
            }
            if (data.containsKey("View"))
            {
                IScreen = (IScreen)data.get("View");
                data.remove("View");
            }
            Resources();
            if (data.containsKey("IHelper"))
            {
                IHelper = (IHelper)data.get("IHelper");
                data.remove("IHelper");
            }
            list = new ArrayList<T>();
        }
        catch (Exception ex)
        {
            LogHelper.Log(ex);
        }
    }

    @Override public void SetCurrent(T v) { current = v; }
    @Override public T GetCurrent() { return current; }
    @Override public void SetCurrentCopy(T v) { currentCopy = v; }
    @Override public T GetCurrentCopy() { return currentCopy; }
    @Override public void SetList(List<T> v) { list = v; }
    @Override public List<T> GetList() { return list; }

    public void Resources()
    {
        lbSelectItem = Bundle.Get("RsMessages", "lbSelectItem");
        lbDeleteEntity = Bundle.Get("RsMessages", "lbDeleteEntity");
    }

    public abstract T CreateNew() throws Exception;
    public T GetItem(T t) throws Exception
    {
        return JLinq.from(list).firstOrDefault(
                p -> ((IEntities)p).Get_Id() == ((IEntities)t).Get_Id());
    }
    public void CreateCopy() throws Exception
    {
        if (current == null)
            return;
        currentCopy = (T)((IEntities)current).GetClone();
    }
    public boolean Comparer(T t1, T t2) throws Exception { return false; }

    public HashMap<String, Object> Load(HashMap<String, Object> data)
    {
        FactoryHelper.IFactoryHelper = FactoryHelper.IFactoryHelper == null ?
            new FactoryHelperContext() : FactoryHelper.IFactoryHelper;
        data.put("Architecture", architecture);
        return data;
    }

    @Override
    public HashMap<String, Object> New(HashMap<String, Object> data)
    {
        HashMap<String, Object> response = new HashMap<String, Object>();
        try
        {
            currentCopy = CreateNew();
            data = data == null ? new HashMap<String, Object>() : data;
            data.put("Action", Action.OPEN);
            IScreen.Change(data);
            return response;
        }
        catch (Exception ex)
        {
            LogHelper.Log(ex);
            response.put("Error", ex.toString());
            return response;
        }
    }

    @Override
    public HashMap<String, Object> Modify(HashMap<String, Object> data)
    {
        HashMap<String, Object> response = new HashMap<String, Object>();
        try
        {
            if (current == null)
                return response;
            CreateCopy();
            data = data == null ? new HashMap<String, Object>() : data;
            data.put("Action", Action.OPEN);
            IScreen.Change(data);
            return response;
        }
        catch (Exception ex)
        {
            LogHelper.Log(ex);
            response.put("Error", ex.toString());
            return response;
        }
    }

    @Override
    public HashMap<String, Object> Select(HashMap<String, Object> data)
    {
        HashMap<String, Object> response = new HashMap<String, Object>();
        try
        {
            isLoading = true;
            IScreen.Loading(Loading.ADD);
            data = data == null ? new HashMap<String, Object>() : data;
            data = Load(data);
            response = IHelper.Select(data);
            if (response == null || !response.containsKey("Entities"))
                return response;
            list = list == null ? new ArrayList<T>() : list;
            list.clear();
            List<T> temp = (List<T>)response.get("Entities");
            for (T item : temp)
                list.add(item);
            //current = list.get(0);
            copy = new ArrayList<T>(list);
            Close(data);
            return response;
        }
        catch (Exception ex)
        {
            LogHelper.Log(ex);
            response.put("Error", ex.toString());
            return response;
        }
        finally
        {
            isLoading = false;
            IScreen.Loading(Loading.REMOVE);
        }
    }

    @Override
    public HashMap<String, Object> Delete(HashMap<String, Object> data)
    {
        HashMap<String, Object> response = new HashMap<String, Object>();
        try
        {
            isLoading = true;
            IScreen.Loading(Loading.ADD);
            data = data == null ? new HashMap<String, Object>() : data;
            data = Load(data);
            if (data.containsKey("Entity"))
                current = (T)data.get("Entity");
            if (!data.containsKey("Entity"))
                data.put("Entity", current);

            if (current == null)
                return response;
            if (!(boolean)MessagesHelper.Show(lbDeleteEntity, Message.QUESTION))
                return response;

            response = IHelper.Delete(data);
            if (response == null || response.containsKey("Error"))
                return response;
            current = (T)response.get("Entity");
            if (current != null)
            {
                list.remove(GetItem(current));
                current = null;
                currentCopy = null;
            }
            Close(data);
            return response;
        }
        catch (Exception ex)
        {
            LogHelper.Log(ex);
            response.put("Error", ex.toString());
            return response;
        }
        finally
        {
            isLoading = false;
            IScreen.Loading(Loading.REMOVE);
        }
    }

    @Override
    public HashMap<String, Object> Save(HashMap<String, Object> data)
    {
        HashMap<String, Object> response = new HashMap<String, Object>();
        try
        {
            isLoading = true;
            IScreen.MoveFocus();
            IScreen.Loading(Loading.ADD);

            data = data == null ? new HashMap<String, Object>() : data;
            data = Load(data);
            if (data.containsKey("Entity"))
                currentCopy = (T)data.get("Entity");
            if (!data.containsKey("Entity"))
                data.put("Entity", currentCopy);

            if (currentCopy == null)
                return response;
            if (((IEntities)currentCopy).Get_Id() == 0)
            {
                response = IHelper.Insert(data);
                if (response == null || response.containsKey("Error"))
                    return response;
                currentCopy = (T)response.get("Entity");
                current = currentCopy;
                list.add(current);
            }
            else if (((IEntities)currentCopy).Get_Id() != 0 &&
                    !Comparer(current, currentCopy))
            {
                response = IHelper.Update(data);
                if (response == null || response.containsKey("Error"))
                    return response;
                currentCopy = (T)response.get("Entity");
                list.remove(GetItem(current));
                list.add(currentCopy);
                currentCopy = null;
            }
            if (response != null || !response.containsKey("Error"))
                Close(data);
            return response;
        }
        catch (Exception ex)
        {
            LogHelper.Log(ex);
            response.put("Error", ex.toString());
            return response;
        }
        finally
        {
            isLoading = false;
            IScreen.Loading(Loading.REMOVE);
        }
    }

    @Override
    public HashMap<String, Object> Close(HashMap<String, Object> data)
    {
        HashMap<String, Object> response = new HashMap<String, Object>();
        try
        {
            data = data == null ? new HashMap<String, Object>() : data;
            data.put("Action", Action.CLOSE);
            IScreen.Change(data);
            return response;
        }
        catch (Exception ex)
        {
            LogHelper.Log(ex);
            response.put("Error", ex.toString());
            return response;
        }
    }
}