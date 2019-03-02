package com.ismailhakkiaydin.cizimislemleri;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.PagerTitleStrip;
import android.view.MotionEvent;
import android.view.View;

public class Tuval extends View {

    private float x1,x2,y1,y2;
    private Context context;

    public Tuval(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p = new Paint();
        p.setColor(Color.BLUE);
        canvas.drawCircle(x2,y2,90,p);
    }

    @Override
    public boolean onFilterTouchEventForSecurity(MotionEvent event) {

        int left,right,top,bottom;

        if (event.getAction() == MotionEvent.ACTION_DOWN){
            x1=event.getX();
            y1=event.getY();
        }

        if (event.getAction()==MotionEvent.ACTION_MOVE){
            x2=event.getX();
            y2=event.getY();
            if (x1<x2){
                left=(int) x1;
                right = (int) x2;
            }
            else {
                left= (int)x2;
                right=(int) x1;
            }
            if (y1<y2){
                top=(int)y1;
                bottom=(int)y2;
            }
            else {
                bottom=(int)y1;
                top=(int)y2;
            }
            this.invalidate(left,top,right,bottom);
        }
        return true;

    }
}
