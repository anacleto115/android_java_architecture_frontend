package com.example.android.Screens;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.android.R;
import java.util.ArrayList;
import java.util.List;

public class main_windowForm extends Fragment
{
    private List<DrawerItems> ListData = new ArrayList<DrawerItems>() { {
        add(new DrawerItems("PersonTypes", 0));
    } };
    private static FragmentManager fragmentManager = null;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.main_window, container, false);
        LoadComponents(rootView);
        return rootView;
    }

    private void LoadComponents(View rootView)
    {
        fragmentManager = getFragmentManager();
        ShowFragment(new person_typesForm());

        ListView drawerList = (ListView)rootView.findViewById(R.id.left_drawer);
        drawerList.setAdapter(new DrawerListAdapter(rootView.getContext(), ListData));
		drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id)
            {
                ChangeScreen(position);
            }
        });
    }

    public void ChangeScreen(int position)
    {
        DrawerItems item = (DrawerItems)ListData.get(position);

        Fragment fragment = null;
        switch (item.getName())
        {
            default: fragment = new person_typesForm(); break;
        }
        ShowFragment(fragment);
    }

    public static void ShowFragment(Fragment fragment)
    {
        fragmentManager.beginTransaction()
            .replace(R.id.content, fragment)
            .commit();
    }

    private class DrawerItems
    {
        private String name;
        private int iconId;

        public DrawerItems(String name, int iconId)
        {
            this.name = name;
            this.iconId = iconId;
        }

        public String getName() { return name; }
        public void setName(String v) { this.name = v; }

        public int getIconId() { return iconId; }
        public void setIconId(int v) { this.iconId = v; }
    }

    private class DrawerListAdapter extends ArrayAdapter
    {
        private List<DrawerItems> ListData;

        public DrawerListAdapter(Context context, List objects)
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
                convertView = inflater.inflate(R.layout.drawer_items, null);
            }

            ImageView icon = (ImageView)convertView.findViewById(R.id.item_icon);
            TextView name = (TextView)convertView.findViewById(R.id.item_name);

            DrawerItems item = ListData.get(position);
            if (item.getIconId() != 0)
                icon.setImageResource(item.getIconId());
            name.setText(com.example.lib_language.Bundle.Get("RsMenu", "lb" + item.getName()));

            return convertView;
        }
    }
}