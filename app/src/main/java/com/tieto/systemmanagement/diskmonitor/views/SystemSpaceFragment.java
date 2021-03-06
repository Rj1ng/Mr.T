package com.tieto.systemmanagement.diskmonitor.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.tieto.systemmanagement.R;
import com.tieto.systemmanagement.TApp;
import com.tieto.systemmanagement.diskmonitor.activities.DiskSWUninstalledActivity;
import com.tieto.systemmanagement.diskmonitor.adapter.DiskStorageAdapter;
import com.tieto.systemmanagement.diskmonitor.data.DiskData;

public class SystemSpaceFragment extends Fragment {
    private ListView listView;
    private DiskStorageAdapter adapter;

    public SystemSpaceFragment() {
        super();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView)view.findViewById(R.id.disk_list);
        adapter = new DiskStorageAdapter((Context)(this.getActivity()), DiskData.getInstance().getSystemSpaceInfos());
        listView.setAdapter(adapter);
        TextView tv_summary_title = (TextView)view.findViewById(R.id.title);
        tv_summary_title.setText(TApp.getInstance().getString(R.string.disk_space_system));

        // Click event for single list row
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0: {//SW uninstalled
                        Intent intent = new Intent(getActivity(),DiskSWUninstalledActivity.class);
                        startActivity(intent);
                        break;
                    }
                    default:
                        break;
                }

            }
        });

        //configured mem usage,how to refactor
        TextView tv_summary_men_percent_used = (TextView)view.findViewById(R.id.used_percent);
        tv_summary_men_percent_used.setText(DiskData.getInstance().getMemPercentUsed()+"%");

        TextView tv_summary_men_free = (TextView)view.findViewById(R.id.used);
        tv_summary_men_free.setText(TApp.getInstance().getString(R.string.disk_space_used_title)
                +DiskData.getInstance().getStorageUsed()+"G/"
                +DiskData.getInstance().getStorageTotal()+"G");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_disk_system_space, container, false);
        return rootView;
 }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
