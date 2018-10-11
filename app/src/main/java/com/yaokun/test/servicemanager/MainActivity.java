package com.yaokun.test.servicemanager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yaokun.test.servicemanager.interfaces.AbsDataModel;
import com.yaokun.test.servicemanager.model.HealthModel;
import com.yaokun.test.servicemanager.utils.DialogsUtils;
import com.yaokun.test.servicemanager.utils.GlobalStorage;
import com.yaokun.test.servicemanager.utils.OkHttpUtils;
import com.yaokun.test.servicemanager.view.TablesView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AbsActivity implements TablesView.OnClickTab {


    TablesView tab_view;
    private boolean auto_refresh;
    private int cycly;
    //    String daad = "{\"result\":[{\"servcieName\":\"esale-cloud-dms\",\"stat\":\"Sl\",\"mem\":\"10.5\",\"port\":\"8673\",\"ip\":\"13.112.64.129\",\"cpu\":\"10.8\",\"pid\":\"5809\",\"portStatus\":\"ok\",\"time\":\"109:43\",\"serviceId\":null,\"status\":\"UP\"},{\"servcieName\":\"esale-cloud-file\",\"stat\":\"Sl\",\"mem\":\"10.7\",\"port\":\"8606\",\"ip\":\"13.112.64.129\",\"cpu\":\"10.7\",\"pid\":\"5758\",\"portStatus\":\"ok\",\"time\":\"108:29\",\"serviceId\":null,\"status\":\"UP\"},{\"servcieName\":\"esale-cloud-mbs\",\"stat\":\"Sl\",\"mem\":\"10.8\",\"port\":\"8671\",\"ip\":\"13.112.64.129\",\"cpu\":\"10.9\",\"pid\":\"5563\",\"portStatus\":\"ok\",\"time\":\"110:21\",\"serviceId\":null,\"status\":\"UP\"},{\"servcieName\":\"esale-cloud-oms\",\"stat\":\"Sl\",\"mem\":\"10.5\",\"port\":\"8674\",\"ip\":\"13.112.64.129\",\"cpu\":\"10.7\",\"pid\":\"4939\",\"portStatus\":\"ok\",\"time\":\"108:48\",\"serviceId\":null,\"status\":\"UP\"},{\"servcieName\":\"esale-cloud-pbs\",\"stat\":\"Sl\",\"mem\":\"4.7\",\"port\":\"8672\",\"ip\":\"13.112.64.129\",\"cpu\":\"10.9\",\"pid\":\"32609\",\"portStatus\":\"ok\",\"time\":\"73:07\",\"serviceId\":null,\"status\":\"UP\"},{\"servcieName\":\"esale-cloud-service\",\"stat\":\"Sl\",\"mem\":\"3.5\",\"port\":\"8605\",\"ip\":\"13.112.64.129\",\"cpu\":\"5.5\",\"pid\":\"28619\",\"portStatus\":\"ok\",\"time\":\"37:12\",\"serviceId\":null,\"status\":\"UP\"},{\"servcieName\":\"esale-cloud-ums\",\"stat\":\"Sl\",\"mem\":\"10.5\",\"port\":\"8670\",\"ip\":\"13.112.64.129\",\"cpu\":\"10.8\",\"pid\":\"3659\",\"portStatus\":\"ok\",\"time\":\"109:07\",\"serviceId\":null,\"status\":\"UP\"},{\"servcieName\":\"esale-cloud-dms\",\"stat\":\"Sl\",\"mem\":\"10.5\",\"port\":\"8673\",\"ip\":\"18.182.20.239\",\"cpu\":\"11.0\",\"pid\":\"30803\",\"portStatus\":\"ok\",\"time\":\"111:24\",\"serviceId\":null,\"status\":\"UP\"},{\"servcieName\":\"esale-cloud-file\",\"stat\":\"Sl\",\"mem\":\"10.7\",\"port\":\"8606\",\"ip\":\"18.182.20.239\",\"cpu\":\"10.7\",\"pid\":\"30716\",\"portStatus\":\"ok\",\"time\":\"108:22\",\"serviceId\":null,\"status\":\"UP\"},{\"servcieName\":\"esale-cloud-mbs\",\"stat\":\"Sl\",\"mem\":\"10.5\",\"port\":\"8671\",\"ip\":\"18.182.20.239\",\"cpu\":\"11.2\",\"pid\":\"30235\",\"portStatus\":\"ok\",\"time\":\"113:24\",\"serviceId\":null,\"status\":\"UP\"},{\"servcieName\":\"esale-cloud-oms\",\"stat\":\"Sl\",\"mem\":\"11.5\",\"port\":\"8674\",\"ip\":\"18.182.20.239\",\"cpu\":\"10.8\",\"pid\":\"29237\",\"portStatus\":\"ok\",\"time\":\"109:15\",\"serviceId\":null,\"status\":\"UP\"},{\"servcieName\":\"esale-cloud-pbs\",\"stat\":\"Sl\",\"mem\":\"2.4\",\"port\":\"8672\",\"ip\":\"18.182.20.239\",\"cpu\":\"12.0\",\"pid\":\"28425\",\"portStatus\":\"ok\",\"time\":\"80:38\",\"serviceId\":null,\"status\":\"UP\"},{\"servcieName\":\"esale-cloud-service\",\"stat\":\"Sl\",\"mem\":\"5.2\",\"port\":\"8605\",\"ip\":\"18.182.20.239\",\"cpu\":\"5.8\",\"pid\":\"1232\",\"portStatus\":\"ok\",\"time\":\"39:31\",\"serviceId\":null,\"status\":\"UP\"},{\"servcieName\":\"esale-cloud-ums\",\"stat\":\"Sl\",\"mem\":\"9.9\",\"port\":\"8670\",\"ip\":\"18.182.20.239\",\"cpu\":\"7.7\",\"pid\":\"28844\",\"portStatus\":\"ok\",\"time\":\"42:29\",\"serviceId\":null,\"status\":\"UP\"},{\"servcieName\":\"esale-cloud-dms\",\"stat\":\"Sl\",\"mem\":\"10.4\",\"port\":\"8673\",\"ip\":\"13.114.162.152\",\"cpu\":\"9.9\",\"pid\":\"32712\",\"portStatus\":\"ok\",\"time\":\"100:22\",\"serviceId\":null,\"status\":\"UP\"},{\"servcieName\":\"esale-cloud-file\",\"stat\":\"Sl\",\"mem\":\"10.7\",\"port\":\"8606\",\"ip\":\"13.114.162.152\",\"cpu\":\"9.8\",\"pid\":\"32662\",\"portStatus\":\"ok\",\"time\":\"99:48\",\"serviceId\":null,\"status\":\"UP\"},{\"servcieName\":\"esale-cloud-mbs\",\"stat\":\"Sl\",\"mem\":\"9.7\",\"port\":\"8671\",\"ip\":\"13.114.162.152\",\"cpu\":\"9.7\",\"pid\":\"32158\",\"portStatus\":\"ok\",\"time\":\"98:21\",\"serviceId\":null,\"status\":\"UP\"},{\"servcieName\":\"esale-cloud-oms\",\"stat\":\"Sl\",\"mem\":\"10.4\",\"port\":\"8674\",\"ip\":\"13.114.162.152\",\"cpu\":\"9.7\",\"pid\":\"31093\",\"portStatus\":\"ok\",\"time\":\"98:35\",\"serviceId\":null,\"status\":\"UP\"},{\"servcieName\":\"esale-cloud-pbs\",\"stat\":\"Sl\",\"mem\":\"3.9\",\"port\":\"8672\",\"ip\":\"13.114.162.152\",\"cpu\":\"10.5\",\"pid\":\"28302\",\"portStatus\":\"ok\",\"time\":\"70:12\",\"serviceId\":null,\"status\":\"UP\"},{\"servcieName\":\"esale-cloud-service\",\"stat\":\"Sl\",\"mem\":\"5.6\",\"port\":\"8605\",\"ip\":\"13.114.162.152\",\"cpu\":\"5.2\",\"pid\":\"31686\",\"portStatus\":\"ok\",\"time\":\"35:07\",\"serviceId\":null,\"status\":\"UP\"},{\"servcieName\":\"esale-cloud-ums\",\"stat\":\"Sl\",\"mem\":\"10.4\",\"port\":\"8670\",\"ip\":\"13.114.162.152\",\"cpu\":\"9.8\",\"pid\":\"28427\",\"portStatus\":\"ok\",\"time\":\"99:31\",\"serviceId\":null,\"status\":\"UP\"},{\"servcieName\":\"esale-cloud-dms\",\"stat\":\"Sl\",\"mem\":\"10.6\",\"port\":\"8673\",\"ip\":\"54.65.170.38\",\"cpu\":\"9.8\",\"pid\":\"16873\",\"portStatus\":\"ok\",\"time\":\"\n";

    int time_z = 0;
    boolean f = true;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tab_view = findViewById(R.id.tab_view);
        initData();
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (f) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (auto_refresh) {
                        time_z++;
                        if (time_z == cycly) {
                            time_z = 0;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    getData();
                                }
                            });
                        }

                    }
                }
            }
        }).start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void initData() {

        tab_view.setHeadColor(getResources().getColor(R.color.tab));
        ArrayList<String> strings = new ArrayList<>();
        strings.add("服务ID");
        strings.add("CUP(%)");
        strings.add("STAT");
        strings.add("内存(G)");
        strings.add("端口");
        strings.add("IP_Address");
        strings.add("PID");
        strings.add("端口状态");
        strings.add("时间");
        strings.add("状态");
        tab_view.setTabHeads(strings);
        String value = GlobalStorage.getValue("ttttttt", "");
        if (!TextUtils.isEmpty(value)) {
            HealthModel healthModel = new Gson().fromJson(value, HealthModel.class);
            tab_view.setDatas(healthModel.getResult());

        }
        tab_view.setOnClickTab(this);
        getData();
    }

    private void getData() {
        String url = "http://182.61.171.16:8099/health?token=eyJzdWIiOiIxNTMyMDU2Njk0OTgyNjc5QDE1MzIwNTY2OTQ5ODI2NzlAMUAxNTM4MjkwNjcwMDg3In0";
        url = GlobalStorage.getValue("url", url);
        OkHttpUtils.getAsyncRequest(MainActivity.this, url, this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onResume() {
        super.onResume();
        tab_view.refresh();
        onReset();
    }

    private void onReset() {
        auto_refresh = GlobalStorage.getValue("auto_refresh", false);
        cycly = GlobalStorage.getValue("cycle", 60);


    }


    @Override
    public int getBody() {
        return R.layout.activity_main;
    }

    @Override
    public String getTitel() {
        return "主页";
    }

    @Override
    public int getRightIcon() {
        return R.drawable.settings;
    }

    @Override
    public void clickRight(View view) {
        startActivityForResult(new Intent(this, SettingsActivity.class), 1001);
    }

    @Override
    public void clickTitel(View view) {
        getData();
    }

    @Override
    public void onClickCell(List<? extends AbsDataModel> mod, int row, int column) {
        Toast.makeText(this, mod.get(row).getDatalist().get(column), Toast.LENGTH_SHORT).show();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void postResult(String result) {
        Log.e("postResult", "===========>> " + result);
        GlobalStorage.update("ttttttt", result);
        HealthModel healthModel = new Gson().fromJson(result, HealthModel.class);
        tab_view.setDatas(healthModel.getResult());
    }


}
