
package com.example.android.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.android.R;
import com.example.lib_domain_entities.Models.PersonTypes;
import java.util.List;

public class PersonTypesListAdapter extends ArrayAdapter
{
    private List<PersonTypes> ListData;

    public PersonTypesListAdapter(Context context, List objects)
    {
        super(context, 0, objects);
        this.ListData = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)parent.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dt_person_types, null);
        }

        TextView lbName = (TextView)convertView.findViewById(R.id.lbName);
        lbName.setText(com.example.lib_language.Bundle.Get("RsPersonTypes", "clName"));

        PersonTypes item = ListData.get(position);

        TextView name = (TextView)convertView.findViewById(R.id.name);
        name.setText(item.GetName());

        return convertView;
    }
}