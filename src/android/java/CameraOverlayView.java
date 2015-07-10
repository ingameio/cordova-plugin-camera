package io.ingame.squarecamera;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;

public class CameraOverlayView extends View {

    public CameraOverlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onDraw(Canvas canvas) {
        Activity activity = (Activity) this.getContext();
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int h = size.y;
        int w = size.x;

        int sq = Math.min(h, w);
        int l = (w - sq) / 2;
        int t = 0;
        int r = l + sq;
        int b = t + sq;
        Rect innerRect = new Rect(l, t, r, b);
        Rect outerRect = new Rect(0, 0, w, h);

        //Rect above = new Rect(outerRect.left, outerRect.top, innerRect.right, innerRect.top);
        Rect left = new Rect(outerRect.left, innerRect.top, innerRect.left, innerRect.bottom);
        Rect right = new Rect(innerRect.right, innerRect.top, outerRect.right, innerRect.bottom);
        Rect bottom = new Rect(outerRect.left, innerRect.bottom, outerRect.right, outerRect.bottom);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.BLACK);
        //canvas.drawRect(above, paint);
        canvas.drawRect(left, paint);
        canvas.drawRect(right, paint);
        canvas.drawRect(bottom, paint);
    }
}
