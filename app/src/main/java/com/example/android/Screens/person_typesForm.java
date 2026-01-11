
package com.example.android.Screens;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.example.android.Adapters.PersonTypesListAdapter;
import com.example.android.R;
import com.example.lib_domain_context.Enumerables;
import com.example.lib_domain_entities.Models.PersonTypes;
import com.example.lib_presentation_context.VwModels.PersonTypesViewModel;
import com.example.lib_domain_context.IScreen;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class person_typesForm extends Fragment implements IScreen
{
    private Button btNew, btLoad;
    private TextView lbTitle;
    private ListView listData;
    private List<PersonTypes> _list = null;
    private PersonTypesViewModel iViewModel;
    private Context context;

    public person_typesForm()
    {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("View", this);
        iViewModel = new PersonTypesViewModel(data);
    }

    @Override public void Loading(Enumerables.Loading action) { }
    @Override public void MoveFocus() { }
    @Override public void Change(HashMap<String, Object> data) { }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.person_types, container, false);
        LoadComponents(rootView);
        return rootView;
    }

    private void LoadComponents(View rootView)
    {
        context = rootView.getContext();

        lbTitle = (TextView)rootView.findViewById(R.id.lbTitle);
        btLoad = (Button)rootView.findViewById(R.id.btLoad);
        btNew = (Button)rootView.findViewById(R.id.btNew);
        lbTitle.setText(com.example.lib_language.Bundle.Get("RsPersonTypes", "lbTitle"));
        btNew.setText(com.example.lib_language.Bundle.Get("RsMenu", "lbNew"));
        btLoad.setText(com.example.lib_language.Bundle.Get("RsMenu", "lbSelect"));
        btLoad.setOnClickListener(x -> Load());

        listData = (ListView)rootView.findViewById(R.id.list_data);
        listData.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id)
            {

            }
        });
        Load();
    }

    private void Load()
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable()
        {
            @Override
            public void run()
            {
                HashMap<String, Object> data = new HashMap<String, Object>();
                HashMap<String, Object> response = iViewModel.Select(data);
                if (response == null || response.containsKey("Error"))
                    return ;
                _list = (List<PersonTypes>) response.get("Entities");

                handler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        listData.setAdapter(new PersonTypesListAdapter(context, _list));
                    }
                });
            }
        });
    }
}