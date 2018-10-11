package com.yaokun.test.servicemanager.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Environment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.yaokun.test.servicemanager.R;

import java.io.File;
import java.io.FileOutputStream;

public class DialogsUtils {


    public static Dialog CreatDialog(Context context, String titel) {
        Dialog dialog = new Dialog(context, R.style.Theme_Transparent);
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_window, null);
        TextView tieee = (TextView) inflate.findViewById(R.id.titel);
        if (!TextUtils.isEmpty(titel)) {
            tieee.setText(titel);
        }
        RotateAnimation animation =
                new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(800);
        animation.setRepeatMode(Animation.RESTART);
        animation.setFillAfter(true);
        animation.setRepeatCount(650);
//        animation.setInterpolator(new LinearInterpolator());
        animation.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float v) {
                return (float) ((Math.pow(v, 1.7)) );
            }
        });
        inflate.findViewById(R.id.pro_img).startAnimation(animation);
        dialog.getWindow().setContentView(inflate);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        dialog.show();
        dialog.getWindow().setLayout(dip2px(context, 200), dip2px(context, 120));
        return dialog;
    }

    public static int dip2px(Context context, int dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }


    public static void bitmapRotate(float degrees, Bitmap baseBitmap) {
        // 创建一个和原图一样大小的图片
        Bitmap afterBitmap = Bitmap.createBitmap(baseBitmap.getWidth(),
                baseBitmap.getHeight(), baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        Matrix matrix = new Matrix();
        // 根据原图的中心位置旋转
        matrix.setRotate(degrees, baseBitmap.getWidth() / 2,
                baseBitmap.getHeight() / 2);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawBitmap(baseBitmap, matrix, paint);
        File file = new File(Environment.getExternalStorageDirectory(), File.separator + "icon.png");
        try {
            file.createNewFile();
            afterBitmap.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
