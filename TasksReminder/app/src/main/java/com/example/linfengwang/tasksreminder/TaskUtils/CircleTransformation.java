package com.example.linfengwang.tasksreminder.TaskUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.squareup.picasso.Transformation;

public class CircleTransformation implements Transformation {

    private final int outerColor;
    private final int innerColor;
    private final int borderRadius;

    public CircleTransformation() {
        this(Color.WHITE, Color.WHITE);
    }

    CircleTransformation(int outerColor, int innerColor) {
        this(outerColor, innerColor, 5);
    }

    CircleTransformation(int outerColor, int innerColor, int borderRadius) {
        this.outerColor = outerColor;
        this.innerColor = innerColor;
        this.borderRadius = borderRadius;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());

        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        if (squaredBitmap != source) {
            source.recycle();
        }

        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(
                squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP
        );
        paint.setShader(shader);
        paint.setAntiAlias(true);

        float r = size / 2f;

        // Prepare the background
        Paint paintBg = new Paint();
        paintBg.setColor(outerColor);
        paintBg.setAntiAlias(true);

        // Draw the outer background circle
        canvas.drawCircle(r, r, r, paintBg);
        // Draw the inner background circle
        paintBg.setColor(innerColor);
        canvas.drawCircle(r, r, r - borderRadius * 2, paintBg);

        // Draw the image smaller than the background so a little border will be seen
        canvas.drawCircle(r, r, r - borderRadius * 4, paint);

        squaredBitmap.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        return "circle";
    }
}