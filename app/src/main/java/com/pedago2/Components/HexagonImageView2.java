package com.pedago2.Components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;

public class HexagonImageView2 extends android.support.v7.widget.AppCompatImageView {

    private Path hexagonPath;
    private Path hexagonBorderPath;
    private float radius;
    private Bitmap image;
    private int viewWidth;
    private int viewHeight;
    private Paint paint;
    private BitmapShader shader;
    private Paint paintBorder;
    private int borderWidth = 4;

    public HexagonImageView2(Context context) {
        super(context);
        setup();
    }

    public HexagonImageView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public HexagonImageView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    private void setup() {
        paint = new Paint();
        paint.setAntiAlias(true);

        paintBorder = new Paint();
        setBorderColor(Color.WHITE);
        paintBorder.setAntiAlias(true);
        this.setLayerType(LAYER_TYPE_SOFTWARE, paintBorder);
        paintBorder.setShadowLayer(4.0f, 1.0f, 1.0f, Color.BLACK);

        hexagonPath = new Path();
        hexagonBorderPath = new Path();
    }

    public void setRadius(float r) {
        this.radius = r;
        calculatePath();
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        this.invalidate();
    }

    public void setBorderColor(int borderColor) {
        if (paintBorder != null)
            paintBorder.setColor(borderColor);

        this.invalidate();
    }

    private void calculatePath() {
//x kanan y atas
        float triangleHeight = (float) (Math.sqrt(3) * radius / 2);
        float centerX = viewWidth / 2;
        float centerY = viewHeight / 2;

        Log.d("itung", "triangleHeight " + triangleHeight);
        Log.d("itung", "centerX " + centerX);
        Log.d("itung", "centerY " + centerY);
        Log.d("itung", "radius " + radius);

        hexagonBorderPath.moveTo(centerX, centerY + radius);
        hexagonBorderPath.lineTo(centerX - triangleHeight, centerY + radius / 2);
        hexagonBorderPath.lineTo(centerX - triangleHeight, centerY - radius / 2);
        hexagonBorderPath.lineTo(centerX, centerY - radius);
        hexagonBorderPath.lineTo(centerX + triangleHeight, centerY - radius / 2);
        hexagonBorderPath.lineTo(centerX + triangleHeight, centerY + radius / 2);
        hexagonBorderPath.moveTo(centerX, centerY + radius);

        Log.d("itung", "moveTo " + centerX + " " + (centerY + radius));
        Log.d("itung", "lineTo " + (centerX - triangleHeight) + " " + (centerY + radius / 2));
        Log.d("itung", "lineTo " + (centerX - triangleHeight) + " " + (centerY - radius / 2));
        Log.d("itung", "lineTo " + (centerX) + " " + (centerY - radius));
        Log.d("itung", "lineTo " + (centerX + triangleHeight) + " " + (centerY - radius / 2));
        Log.d("itung", "lineTo " + (centerX + triangleHeight) + " " + (centerY + radius / 2));
        Log.d("itung", "moveTo " + (centerX) + " " + (centerY + radius));

        float radiusBorder = radius - borderWidth;
        float triangleBorderHeight = (float) (Math.sqrt(3) * radiusBorder / 2);

//        hexagonPath.moveTo(centerX, centerY + radiusBorder);
//        hexagonPath.lineTo(centerX - triangleBorderHeight, centerY + radiusBorder / 2);
//        hexagonPath.lineTo(centerX - triangleBorderHeight, centerY - radiusBorder / 2);
//        hexagonPath.lineTo(centerX, centerY - radiusBorder);
//        hexagonPath.lineTo(centerX + triangleBorderHeight, centerY - radiusBorder / 2);
//        hexagonPath.lineTo(centerX + triangleBorderHeight, centerY + radiusBorder / 2);
//        hexagonPath.moveTo(centerX, centerY + radiusBorder);

//        hexagonPath.moveTo(centerX, centerY + radiusBorder);
//        hexagonPath.lineTo(50, 100);
//        hexagonPath.lineTo(100, 50);
//        hexagonPath.lineTo(50, 100);

        hexagonPath.moveTo(centerX, centerY + radiusBorder);

//        PR
//        Point point1_draw = new Point(75, 0);
//        Point point2_draw = new Point(0, 50);
//        Point point3_draw = new Point(0, 100);
//        Point point4_draw = new Point(75, 150);
//        Point point5_draw = new Point(150, 100);
//        Point point6_draw = new Point(150, 50);
//
//        Path path = new Path();
//        path.moveTo(point1_draw.x, point1_draw.y);

        hexagonPath.moveTo(50, 10);
        hexagonPath.lineTo(10, 50);
        hexagonPath.lineTo(10, 100);

        hexagonPath.lineTo(50, 130);
        hexagonPath.lineTo(120, 100);
        hexagonPath.lineTo(120, 50);
//        hexagonPath.lineTo(centerX - 20, centerX - centerX);

//        hexagonPath.lineTo(centerX, centerY - radiusBorder);
//        hexagonPath.lineTo(centerX + triangleBorderHeight, centerY - radiusBorder / 2);
//        hexagonPath.lineTo(centerX + triangleBorderHeight, centerY + radiusBorder / 2);
//        hexagonPath.moveTo(centerX, centerY + radiusBorder);

        invalidate();
    }

    private void loadBitmap() {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) this.getDrawable();

        if (bitmapDrawable != null)
            image = bitmapDrawable.getBitmap();
    }

    @SuppressLint("DrawAllocation")
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        loadBitmap();

        // init shader
        if (image != null) {

            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

            shader = new BitmapShader(Bitmap.createScaledBitmap(image, canvas.getWidth(), canvas.getHeight(), false), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

            paint.setShader(shader);

            float centerX = viewWidth / 2;
            float centerY = viewHeight / 2;

            canvas.save();
            canvas.rotate(90, centerX, centerY);
            canvas.drawPath(hexagonBorderPath, paintBorder);
            canvas.restore();

            canvas.save();
            canvas.drawPath(hexagonPath, paint);
////            canvas.rotate(90, centerX,centerY);
//            canvas.save(Canvas.MATRIX_SAVE_FLAG); //Saving the canvas and later restoring it so only this image will be rotated.
//            canvas.rotate(90, centerX, centerY);
//            canvas.drawBitmap(Bitmap.createScaledBitmap(image, canvas.getWidth(), canvas.getHeight(), false), centerX, centerY, null);
//            canvas.restore();
//            canvas.restore();
        }

    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec, widthMeasureSpec);

        viewWidth = width - (borderWidth * 2);
        viewHeight = height - (borderWidth * 2);

        radius = height / 2 - borderWidth;

        calculatePath();

        setMeasuredDimension(width, height);
    }

    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = viewWidth;
        }

        return result;
    }

    private int measureHeight(int measureSpecHeight, int measureSpecWidth) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpecHeight);
        int specSize = MeasureSpec.getSize(measureSpecHeight);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = viewHeight;
        }

        return (result + 2);
    }


}