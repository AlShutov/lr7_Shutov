package com.example.Shutov.lab6;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.graphics.Paint.Style;
import android.view.ScaleGestureDetector;
import android.view.View;

class DrawView extends View {

    Paint paint;
    Path path;
    private float scaleFactor = 1.0f;
    private ScaleGestureDetector scaleGestureDetector;

    public DrawView(Context context) {
        super(context);
        setFocusable(true);
        paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        path = new Path();
        scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    @Override
    protected void onDraw(Canvas canvas) {

        float mid = getWidth() / 2;
        float min = Math.min(getWidth()/2, getHeight());
        float half = min/2;
        float width = getWidth();
        float height = getHeight();

        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        // circle
        //Heart
        path.moveTo(width / 2, height / 5);

        // Upper left path
        path.cubicTo(5 * width / 14, 0,
                0, height / 15,
                width / 28, 2 * height / 5);

        // Lower left path
        path.cubicTo(width / 14, 2 * height / 3,
                3 * width / 7, 5 * height / 6,
                width / 2, height);

        // Lower right path
        path.cubicTo(4 * width / 7, 5 * height / 6,
                13 * width / 14, 2 * height / 3,
                27 * width / 28, 2 * height / 5);

        // Upper right path
        path.cubicTo(width, height / 15,
                9 * width / 14, 0,
                width / 2, height / 5);

        paint.setColor(Color.RED);
        paint.setStyle(Style.FILL);
        canvas.save();
        canvas.scale(scaleFactor, scaleFactor);
        canvas.drawPath(path, paint);
        canvas.restore();
        path.close();

        super.onDraw(canvas);


        // star
        mid = mid - half;
        path.reset();
        paint.setColor(Color.rgb(192, 192, 192));
        // top left
        path.moveTo(mid + half * 0.5f, half * 0.84f + 500f);
        // top right
        path.lineTo(mid + half * 1.5f, half * 0.84f + 500f);
        // bottom left
        path.lineTo(mid + half * 0.68f, half * 1.45f + 500f);
        // top tip
        path.lineTo(mid + half * 1.0f, half * 0.5f + 500f);
        // bottom right
        path.lineTo(mid + half * 1.32f, half * 1.45f + 500f);
        // top left
        path.lineTo(mid + half * 0.5f, half * 0.84f + 500f);
        canvas.save();
        canvas.scale(scaleFactor, scaleFactor);
        canvas.drawPath(path, paint);
        canvas.restore();
        path.close();

        super.onDraw(canvas);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        invalidate();
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 10.0f));
            invalidate();
            return true;
        }
    }
}
