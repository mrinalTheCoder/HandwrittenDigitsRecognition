package com.numbers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

public class DrawingView extends ImageView {
    private Paint paint = new Paint();
    private Path path = new Path();

    private Bitmap imageBitmap = null;
    //private Canvas imageCanvas = null;
    //private Paint imageCanvasBg = new Paint();

    public DrawingView(Context context, AttributeSet attrs) {

        super(context, attrs);
        //imageCanvasBg.setColor(Color.WHITE);

        paint.setAntiAlias(true);
        paint.setStrokeWidth(70f);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(DrawingView.class.getName(), "onSizeChanged: Creating imageBitmap of w and h = " + w + ", " + h);
        //imageBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        //imageCanvas = new Canvas(imageBitmap);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //imageCanvas.drawPath(path, paint);
        //imageCanvas.drawBitmap(imageBitmap, 0, 0, imageCanvasBg);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float touchX = event.getX();
        float touchY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(DrawingView.class.getName(), "on Touch Down: Creating imageBitmap of w and h = "
                        + getWidth() + ", " + getHeight());

                //imageBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
                //imageCanvas = new Canvas(imageBitmap);

                path.reset();
                path.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                path.lineTo(touchX, touchY);
                //imageCanvas.drawPath(path, paint);
                path.reset();
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }

    public Bitmap getBitmap() {
        Log.i(DrawingView.class.getName(), "getBitmap: Returning bitmap");
        return imageBitmap;
    }

    public void clear() {
        path.reset();
        invalidate();
    }
}
