package com.tieto.systemmanagement.diskmonitor.views;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.tieto.systemmanagement.R;
import com.tieto.systemmanagement.diskmonitor.adapter.DiskAppAdapter;
import com.tieto.systemmanagement.diskmonitor.data.DiskData;
import com.tieto.systemmanagement.diskmonitor.entity.TApplicationInfo;
import com.tieto.systemmanagement.diskmonitor.model.AsyncItemRemovedPermission;
import com.tieto.systemmanagement.diskmonitor.utils.FileUtils;

import java.net.URL;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DiskAppsInstalledFragment extends Fragment {
    private ListView listView;
    private DiskAppAdapter adapter;
    private Context mContext;
    private SweetAlertDialog mDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_disk_packages_installed, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = (ListView)getView().findViewById(R.id.listView);
        mContext = this.getActivity();

        mDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
        mDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mDialog.setTitleText("卖力获取中");
        mDialog.setCancelable(false);
        mDialog.show();

        final ImageButton btnCleanup = (ImageButton) getView().findViewById(R.id.btnCleanup);
        btnCleanup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String[] pathSelected =  FileUtils.getItemSelected(adapter.getItemsChecked(),
                        adapter.getItemsPath());

                try {
                    if(pathSelected.length > 0) {
                        new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("删除安装包")
                                .setContentText("删除后，安装包将不可找回，确认删除")
                                .setCancelText("取消")
                                .setConfirmText("任性一次")
                                .showCancelButton(true)
                                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.cancel();
                                    }
                                })
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.cancel();
                                        AsyncItemRemovedPermission itemRemoved = new AsyncItemRemovedPermission(mContext, adapter);
                                        itemRemoved.execute(pathSelected);
                                    }
                                })
                                .show();
                    }
                }
                catch (Exception e) {

                }
             }
        });

        ASyncApps syncPackages = new ASyncApps();
        syncPackages.execute();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    class ASyncApps extends AsyncTask<URL, Integer, List<TApplicationInfo>> {
        protected List<TApplicationInfo> doInBackground(URL... arg0) {
            return DiskData.getInstance().getApps(true);
        }

        protected void onPostExecute(List<TApplicationInfo> result) {
            try {
                adapter = new DiskAppAdapter(mContext,result);
                listView.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();
            }

            mDialog.dismiss();
        }
    }
}


