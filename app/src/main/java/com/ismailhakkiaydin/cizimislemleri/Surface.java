package com.ismailhakkiaydin.cizimislemleri;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class Surface extends View {
    private float x1=0, y1=0, x2=0,y2=0;

    public Surface(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1=event.getX();
                y1=event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                x2=event.getX();
                y2=event.getY();
                this.invalidate((int)x1,(int)y1,(int)x2,(int)y2);
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.GREEN);
        p.setStrokeWidth(2F);
        p.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(x1,y1,x2,y2,p);
        super.onDraw(canvas);
    }
}
