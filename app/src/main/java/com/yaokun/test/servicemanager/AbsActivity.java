package com.yaokun.test.servicemanager;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.litesuits.orm.db.annotation.NotNull;
import com.yaokun.test.servicemanager.interfaces.ResultPoster;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class AbsActivity extends AppCompatActivity implements ResultPoster {

    FrameLayout content_view;
    TextView titel;
    ImageView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs);

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.activity_abs, null);
        content_view = inflate.findViewById(R.id.content_view);
        titel = inflate.findViewById(R.id.titel);
        titel.setText(getTitel());
        menu = inflate.findViewById(R.id.menu);
        menu.setImageResource(getRightIcon());
        inflate.findViewById(R.id.back_up).setOnClickListener(listener);
        inflate.findViewById(R.id.menu).setOnClickListener(listener);
        inflate.findViewById(R.id.titel).setOnClickListener(listener);
        View body = getLayoutInflater().inflate(getBody(), null);
        content_view.addView(body);
        super.setContentView(inflate);
    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.back_up) {
                onBackPressed();
            } else if (view.getId() == R.id.titel) {
                clickTitel(view);
            } else if (view.getId() == R.id.menu) {
                clickRight(view);
            } else {
            }

        }
    };

    public abstract int getBody();

    //获取标题
    public abstract String getTitel();

    //获取右边显示图标
    public abstract int getRightIcon();

    public abstract void clickRight(View view);

    public abstract void clickTitel(View view);
}
