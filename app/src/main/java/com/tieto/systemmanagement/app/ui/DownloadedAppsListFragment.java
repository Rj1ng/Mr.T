package com.tieto.systemmanagement.app.ui;


import com.tieto.systemmanagement.app.tools.AppListFragmentTool;


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
