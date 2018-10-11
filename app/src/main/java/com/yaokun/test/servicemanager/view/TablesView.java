package com.yaokun.test.servicemanager.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.Build;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.yaokun.test.servicemanager.interfaces.AbsDataModel;

import java.util.List;

import androidx.annotation.RequiresApi;

public class TablesView extends SurfaceView implements SurfaceHolder.Callback, GestureDetector.OnGestureListener {
    //---------------------------------公共参数-----------------------------------
    String TAG = this.getClass().getSimpleName();
    List<String> tabHeads;
    int Width;//控件宽度px
    int Height;//控件高度px
    int dX = 0, dY = 0;//触摸偏移
    List<? extends AbsDataModel> datas;//数据
    private GestureDetector detector;
    int linesWidth = 2;//表格线的粗细dp
    float dms = 1;//密度
    int headHeigth = 35;//表头高度dp
    int headColor = Color.BLUE;//表头背景色
    int headTextColor = Color.WHITE;//表头文本颜色
    int headTextSize = 16;//表头文本大小
    int liesColor = Color.BLACK;//表格线的颜色
    int backGroungColor = Color.GRAY;//VIew 背景
    private SurfaceHolder holder;
    private Paint paint;
    int sheetWidth = Width;
    int touchMode = 0;//触摸模式，0=触摸的是表格，，1=触摸表头的列分割线，2=触摸行分割线
    int touchYLinesPos = 0;//触摸到的列分割线位置
    int touchPrecision = 90;//触摸误差
    Vibrator vibrator;//震动器
    int defTextColor = Color.BLACK;
    /*------------------------------------------------列参数---------------------------------------------------*/
    int minColumnWidth = 80;//最小列宽dp
    int[] columnMinWidth = new int[512];//列宽度记录
    int[] columnWidth = new int[512];//列宽度记录
    int[] columnLines = new int[512];//列分割线位置
    int indexWidth = 40;//最左边的索引列宽度

    /*------------------------------------------------行参数---------------------------------------------------*/
    int minRankHeight = 30;//最低（标准）行高
    int sheetHeight = Height;//表格高度px

    /*-------------------------------------------------对外接口-----------------------------------------------------*/
    OnClickTab onClickTab;
    private int indexWidthPx;//索引列宽度px
    private int headHeightPx;//标题高度px

    public void setOnClickTab(OnClickTab onClickTab) {
        this.onClickTab = onClickTab;
    }

    public int getHeadHeigth() {
        return headHeigth;
    }

    public void setHeadHeigth(int headHeigth) {
        this.headHeigth = headHeigth;
    }

    public int getHeadColor() {
        return headColor;
    }

    public void setHeadColor(int headColor) {
        this.headColor = headColor;
    }

    public int getHeadTextColor() {
        return headTextColor;
    }

    public void setHeadTextColor(int headTextColor) {
        this.headTextColor = headTextColor;
    }

    public int getHeadTextSize() {
        return headTextSize;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setHeadTextSize(int headTextSize) {
        this.headTextSize = headTextSize;
        drawView(holder);
    }

    public List<? extends AbsDataModel> getDatas() {
        return datas;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setDatas(List<? extends AbsDataModel> datas) {
        this.datas = datas;
        if (null != datas) {
            int caa = datas.size() * getPxvalue(minRankHeight);
            sheetHeight = caa > sheetHeight ? caa : sheetHeight;
            initIndexWidth(datas.size());
        }
        drawView(holder);
    }


    private void initIndexWidth(int maxCount) {
        if (maxCount > 0 && maxCount < 999) {
            indexWidthPx = getPxvalue(35);
        } else if (maxCount > 999 && maxCount < 9999) {
            indexWidthPx = getPxvalue(45);
        } else if (maxCount > 9999 && maxCount < 99999) {
            indexWidthPx = getPxvalue(55);
        } else {
            indexWidthPx = getPxvalue(65);
        }
    }

    public List<String> getTabHeads() {
        return tabHeads;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void refresh() {
        drawView(holder);
    }

    //设置数据源并初始化相关数据
    public void setTabHeads(List<String> tabHeads) {
        this.tabHeads = tabHeads;
        if (null != tabHeads) {
            int i = 0;
            paint.setTextSize(getPxvalue(headTextSize));
            int wwid = 0;
            for (String s : tabHeads) {
                int sss = getTextWidth(s, paint) + 30;
                int min = getPxvalue(minColumnWidth);
                int fanil = sss < min ? min : sss;
                columnWidth[i++] = fanil;
                columnMinWidth[i - 1] = fanil;
                wwid += fanil;
                columnLines[i - 1] = wwid + linesWidth;
            }
            sheetWidth = wwid > Width ? wwid : Width;
        }
        invalidate();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TablesView(Context context) {
        this(context, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TablesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        dms = context.getResources().getDisplayMetrics().density;
        indexWidthPx = getPxvalue(indexWidth);
        headHeightPx = getPxvalue(headHeigth);
        vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        detector = new GestureDetector(context, this);
        holder = this.getHolder();
        holder.addCallback(this);
        setZOrderOnTop(true);// 设置画布 背景透明  
        holder.setFormat(PixelFormat.TRANSLUCENT);
        setFocusable(true);
        setFocusableInTouchMode(true);
        linesWidth = getPxvalue(linesWidth);
        this.setKeepScreenOn(true);
        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                drawView(holder);

            }
        }, 50);
    }


    //绘制图表
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void drawView(SurfaceHolder holder) {
        Canvas canvas = holder.lockCanvas(new Rect(0, 0, Width, Height));
        paint.setAntiAlias(true);
        if (null == canvas) {
            return;
        }
        canvas.drawColor(backGroungColor);
        drawSheet(canvas, paint);
        drawTabHead(canvas, paint);
        drawIndex(canvas, paint);
        holder.unlockCanvasAndPost(canvas);
    }

    //画索引列
    private void drawIndex(Canvas canvas, Paint paint) {
        int indW = indexWidthPx;
        paint.setColor(headColor);
        canvas.drawRect(0, 0, indW, Height, paint);
        paint.setColor(Color.WHITE);
        for (int i = 1; i <= datas.size(); i++) {
            int pxvalue = getPxvalue(minRankHeight);
            if (headHeightPx + dY + i * pxvalue < pxvalue * 2 || headHeightPx + dY + i * pxvalue > Height + pxvalue) {
                continue;
            }
            String s = String.valueOf(i);
            int textWidth = (indW / 2) - getTextWidth(s, paint) / 2;
            int hh = (getTextHeight(s, paint)) + (minRankHeight / 2);
            canvas.drawText(s, 0, s.length(), textWidth, pxvalue * i + hh + dY + headHeightPx - pxvalue, paint);

        }

    }

    //初始化表头
    private void drawTabHead(Canvas canvas, Paint p) {
        int pxvalue = headHeightPx;
        p.setColor(headColor);
        if (null == tabHeads) {
            p.setTextSize(getPxvalue(25));
            canvas.drawText("No Data", getPxvalue(40), getPxvalue(40), p);
            return;
        }
        canvas.drawRect(0, 0, 0 + Width, 0 + pxvalue, p);//绘制表头背景
        int indexW = indexWidthPx;
        for (int i = 0; i < tabHeads.size(); i++) {
            String s = tabHeads.get(i);
            int startX = getStartX(i) + dX + indexW;
            p.setTextSize(getPxvalue(headTextSize));
            p.setColor(headTextColor);
            drawText(canvas, p, startX, 0, columnWidth[i], pxvalue, s);
        }

    }

    //计算X坐标
    private int getStartX(int pos) {
        int x = 0;
        for (int i = 0; i < pos; i++) {
            x += columnWidth[i];
            x += linesWidth;
        }
        return x;
    }

    //绘制文本和分割线
    private void drawText(Canvas canvas, Paint paint, int X, int Y, int W, int H, String text) {
        if (!TextUtils.isEmpty(text)) {
            int textWidth = getTextWidth(text, paint);
            int textHeight = getTextHeight(text, paint);
            int tx = X;
            int ty = Y;
            int len = text.length();
            if (textWidth < W) {
                tx = X + (W - (textWidth)) / 2;
                ty = Y + (H - (textHeight)) / 2;
            } else {
                tx = X;
                ty = Y + (H - (textHeight)) / 2;
                float i = W * 100 / textWidth;
                len = (int) (text.length() * i / 100);
            }
            canvas.drawText(text, 0, len, tx, ty + (textHeight), paint);
        }
        paint.setColor(liesColor);
        canvas.drawLine(X + W, Y, X + W, Y + H, paint);

    }


    //初始化表格
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void drawSheet(Canvas canvas, Paint p) {
        if (null == datas || datas.size() == 0) {
            return;
        }
        for (int i = 0; i < datas.size(); i++) {
            int pxvalue = getPxvalue(minRankHeight);
            if (headHeightPx + dY + i * pxvalue < 0 || headHeightPx + dY + i * pxvalue > Height) {
                continue;
            }
            AbsDataModel model = datas.get(i);
            p.setColor(model.getCellColor(i));
            if ((i & 1) == 0) {
                p.setColor(Color.WHITE);
            } else {
                p.setColor(Color.GRAY);
            }
            drawRank(canvas, p, dX + indexWidthPx, headHeightPx + dY + i * pxvalue, sheetWidth > Width ? sheetWidth : Width, pxvalue, model);
        }
    }

    //画行
    private void drawRank(Canvas canvas, Paint p, int X, int Y, int W, int H, AbsDataModel datas) {
        canvas.drawRect(0, Y, W, Y + H, p);
        for (int i = 0; i < datas.getDatalist().size(); i++) {
            if (getStartX(i + 1) + dX < 0 || getStartX(i) + dX > Width) {
                continue;
            }

            int cellColor = datas.getTextColor(i);
            if (cellColor == 0) {
                cellColor = defTextColor;
            }
            p.setColor(cellColor);
            int indexW = indexWidthPx;
            drawText(canvas, p, getStartX(i) + dX + indexW, Y, columnWidth[i], H, datas.getDatalist().get(i));

        }

    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Width = right - left;
        Height = bottom - top;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            int wwid = 0;
            for (int i = 0; i < tabHeads.size(); i++) {
                wwid += columnWidth[i];
                wwid += linesWidth;
                columnLines[i] = wwid;
            }
            sheetWidth = wwid > Width ? wwid : Width;
        }
        return detector.onTouchEvent(event);
    }


    @Override
    public boolean onDown(MotionEvent motionEvent) {
        float y = motionEvent.getY();
        float x = motionEvent.getX();
        int pxvalue = headHeightPx;
        if (y < pxvalue) {
            for (int i = 0; i < tabHeads.size(); i++) {
                if (Math.abs(x - (columnLines[i] + dX)) < touchPrecision) {
                    touchMode = 1;
                    touchYLinesPos = i;
                    vibrator.vibrate(50);
                    return true;
                }
            }
            touchMode = 0;
        } else {
            touchMode = 0;
        }
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        touchMode = 0;
        findCellByXY(motionEvent.getX(), motionEvent.getY());
        return true;
    }

    //查找点击的格子
    private void findCellByXY(float x, float y) {
        x -= indexWidthPx;
        for (int i = 0; i < datas.size(); i++) {
            if (headHeightPx + i * getPxvalue(minRankHeight) + dY > y - getPxvalue(minRankHeight)) {
                for (int j = 0; j < datas.get(i).getDatalist().size(); j++) {
                    if (getStartX(j + 1) + dX > x) {
                        if (null != onClickTab) {
                            onClickTab.onClickCell(datas, i, j);
                        }
                        break;
                    }
                }
                break;
            }
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float scrollX, float scrollY) {
        if (touchMode == 0) {//在表格上滑动
            dX -= scrollX;
            if (dX < Width - sheetWidth - indexWidthPx) {//限制右边滑动出界
                dX = Width - sheetWidth - indexWidthPx;
            }
            if (dX > 0) {//限制zuo边滑动出界
                dX = 0;
            }
            dY -= scrollY;
            if (dY > 0) {
                dY = 0;
            }
            if (dY < Height - sheetHeight - headHeightPx) {
                if (Height < sheetHeight) {
                    dY = Height - sheetHeight - headHeightPx;
                } else {
                    dY = 0;
                }
            }

        } else if (touchMode == 1) {//拖拽表头宽度
            columnWidth[touchYLinesPos] -= scrollX;
            if (columnWidth[touchYLinesPos] < minColumnWidth) {
                columnWidth[touchYLinesPos] = minColumnWidth;
            }
        } else if (touchMode == 2) {

        } else {

        }

        drawView(holder);
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float scrollX, float scrollY) {
        return false;
    }

    private int getPxvalue(int dp) {
        return (int) (dms * dp);
    }

    private int getTextWidth(String text, Paint paint) {
        if (TextUtils.isEmpty(text)) {
            return 0;
        } else {
            Rect rect = new Rect();
            paint.getTextBounds(text, 0, text.length(), rect);
            return rect.width();
        }
    }

    private int getTextHeight(String text, Paint paint) {
        if (TextUtils.isEmpty(text)) {
            return 0;
        } else {
            Rect rect = new Rect();
            paint.getTextBounds(text, 0, text.length(), rect);
            return rect.height();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.holder = surfaceHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        drawView(surfaceHolder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    public interface OnClickTab {
        void onClickCell(List<? extends AbsDataModel> mod, int row, int column);
    }
}
