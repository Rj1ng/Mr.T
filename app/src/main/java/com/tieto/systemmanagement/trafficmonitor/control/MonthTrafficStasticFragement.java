package com.tieto.systemmanagement.trafficmonitor.control;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tieto.systemmanagement.R;
import com.tieto.systemmanagement.trafficmonitor.adapter.MonthTrafficStaticAdapter;
import com.tieto.systemmanagement.trafficmonitor.entity.AppInfoEntity;
import com.tieto.systemmanagement.trafficmonitor.entity.TrafficStatsWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jane on 15-3-26.
 */
public class MonthTrafficStasticFragement extends Fragment {
    private ListView mListView;
    private List<AppInfoEntity> mAppInfos;
    private MonthTrafficStaticAdapter mAdapter;
    private TrafficStatsWrapper mTrafficStats;

    public MonthTrafficStasticFragement() {
        super();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView = (ListView) view.findViewById(R.id.firewall_listView);
        mAppInfos = new ArrayList<AppInfoEntity>();
        mTrafficStats = new TrafficStatsWrapper(this.getActivity());
        mAppInfos = mTrafficStats.getmTrafficStaticAppInfoLists();
        mAdapter = new MonthTrafficStaticAdapter(this.getActivity(), mAppInfos);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.t_firewall_fragement,container,false);

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
