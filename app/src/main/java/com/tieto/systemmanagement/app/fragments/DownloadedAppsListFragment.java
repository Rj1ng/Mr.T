package com.tieto.systemmanagement.app.fragments;


import com.tieto.systemmanagement.app.utils.AppListFragmentTool;


/**
 * Created by jinpei on 3/25/15.SS
 * Fragment of app list.The responsibility of this fragme
 */
public class DownloadedAppsListFragment extends AppListFragment {
    @Override
    protected int setAppListType() {
        return AppListFragmentTool.APP_LIST_TYPE_DOWNLOADED;
    }
}
