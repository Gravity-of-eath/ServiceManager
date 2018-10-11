package com.yaokun.test.servicemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.yaokun.test.servicemanager.utils.GlobalStorage;

public class SettingsActivity extends AbsActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    CheckBox auto_refresh;
    EditText token, cycly;
    Button btn_cycle;
    String url_def = "http://182.61.171.16:8099/health?token=eyJzdWIiOiIxNTMyMDU2Njk0OTgyNjc5QDE1MzIwNTY2OTQ5ODI2NzlAMUAxNTM4MjkwNjcwMDg3In0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        findViewById(R.id.btn_token).setOnClickListener(this);
        btn_cycle = findViewById(R.id.btn_cycle);
        btn_cycle.setOnClickListener(this);
        token = findViewById(R.id.et_token);
        cycly = findViewById(R.id.cycle);
        auto_refresh = findViewById(R.id.auto_refresh);
        auto_refresh.setOnCheckedChangeListener(this);
        refresh_view();
    }


    private void refresh_view() {
        int cycle = GlobalStorage.getValue("cycle", 60);
        cycly.setText(cycle + "");
        String url = GlobalStorage.getValue("url", url_def);
        token.setText(url);
        boolean auto = GlobalStorage.getValue("auto_refresh", false);
        auto_refresh.setChecked(auto);
        btn_cycle.setEnabled(auto);
        cycly.setEnabled(auto);

    }

    @Override
    public int getBody() {
        return R.layout.activity_settings;
    }

    @Override
    public String getTitel() {
        return "Settings";
    }

    @Override
    public int getRightIcon() {
        return R.drawable.iconfont1;
    }

    @Override
    public void clickRight(View view) {
        startActivity(new Intent(this, TestActivity.class));
    }

    @Override
    public void clickTitel(View view) {

    }

    @Override
    public void postResult(String result) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_token) {
            String urllll = token.getText().toString();
            if (!TextUtils.isEmpty(urllll)) {
                GlobalStorage.update("url", urllll);
            }
        } else if (view.getId() == R.id.btn_cycle) {
            String cyc = cycly.getText().toString();
            if (!TextUtils.isEmpty(cyc)) {
                int i = Integer.parseInt(cyc);
                if (i <= 30) {
                    i = 30;
                }
                GlobalStorage.update("cycle", i);
            }
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        GlobalStorage.update("auto_refresh", b);
        btn_cycle.setEnabled(b);
        cycly.setEnabled(b);
    }
}
