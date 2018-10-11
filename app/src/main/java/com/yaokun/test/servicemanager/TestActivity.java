package com.yaokun.test.servicemanager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.yaokun.test.servicemanager.interfaces.AbsDataModel;
import com.yaokun.test.servicemanager.view.TablesView;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity implements TablesView.OnClickTab {

    TablesView tabss;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        tabss = findViewById(R.id.tabss);
        tabss.setHeadColor(Color.RED);
        ArrayList<String> hear = new ArrayList<>();
        hear.add("Titel");
        hear.add("Name");
        hear.add("Ages");
        hear.add("Address_local");
        hear.add("Phone");
        hear.add("E-mail");
        hear.add("    ");
        hear.add("    ");
        hear.add("XXXX");

        tabss.setTabHeads(hear);
        ArrayList<AbsDataModel> absDataModels = new ArrayList<>();
        for (int i = 0; i < 1225; i++) {
            VData vd = new VData("Lines" + i, 10);
            absDataModels.add(vd);
        }
        tabss.setDatas(absDataModels);
        tabss.setOnClickTab(this);
    }

    @Override
    public void onClickCell(List<? extends AbsDataModel> mod, int row, int column) {
        Toast.makeText(this, mod.get(row).getDatalist().get(column), Toast.LENGTH_SHORT).show();
    }


    private class VData implements AbsDataModel {
        String name;
        int x;

        public VData(String name, int x) {
            this.name = name;
            this.x = x;
        }

        @Override
        public int getColumnCount() {
            return x;
        }

        @Override
        public int getCellColor(int pos) {
            return Color.DKGRAY;
        }

        @Override
        public int getTextColor(int pos) {
            return (pos & 1) == 1 ? Color.RED : Color.BLACK;
        }

        @Override
        public List<String> getDatalist() {
            ArrayList<String> strings = new ArrayList<>();
            for (int i = 0; i < x; i++) {
                strings.add(name + i);
            }
            return strings;
        }
    }
}
