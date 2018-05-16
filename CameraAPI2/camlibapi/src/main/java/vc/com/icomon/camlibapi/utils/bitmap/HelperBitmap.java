package vc.com.icomon.camlibapi.utils.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
import android.util.Log;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

/**
 * Created by r028367 on 05/07/2017.
 */

public class HelperBitmap {

    public enum CompressFormat {
            JPEG(Bitmap.CompressFormat.JPEG)
        , PNG(Bitmap.CompressFormat.PNG)
        , WEBP(Bitmap.CompressFormat.WEBP);
        private Bitmap.CompressFormat type;
        CompressFormat(Bitmap.CompressFormat type) {
            this.type = type;
        }

        public Bitmap.CompressFormat getType() {
            return type;
        }
    }


    public static byte [] compressDefault(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte [] bytes = byteArrayOutputStream.toByteArray();
        return bytes;
    }

    public static Bitmap toBitmap(Drawable drawable) {
        Bitmap bitmap = null;
        if(drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            bitmap = bitmapDrawable.getBitmap();
            return bitmap;
        }
        else {
            int iWidth = drawable.getIntrinsicWidth();
            int iHeight = drawable.getIntrinsicHeight();
            if(iWidth <=0 || iHeight <=0) {
                // 8 bits para cada canal de cor ARGB (4bytes usandos no total)
                bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
            }
            else {
                bitmap = Bitmap.createBitmap(iWidth, iHeight, Bitmap.Config.ARGB_8888);
            }
        }

        if(!bitmap.isRecycled()) {
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        }

        return bitmap;
    }

    public static Bitmap loadBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap(v.getLayoutParams().width, v.getLayoutParams().height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
        v.draw(c);
        return b;
    }

    public static Bitmap getBitmapFromView(View v) {
        v.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
        v.buildDrawingCache(true);
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false); // clear drawing cache
        return b;
    }

    public static byte [] compressDefault(Bitmap bitmap, int quality) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, quality, byteArrayOutputStream);
        byte [] bytes = byteArrayOutputStream.toByteArray();
        if(!bitmap.isRecycled())
            bitmap.recycle();
        
        return bytes;
    }

    public static byte [] compressMaxQuality(Bitmap bitmap, CompressFormat compressFormat) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat.getType(), 100, byteArrayOutputStream);
        byte [] bytes = byteArrayOutputStream.toByteArray();
        if(!bitmap.isRecycled())
            bitmap.recycle();
        return bytes;
    }

    public static final String DEFAULT_COMPRESSED_FILE_NAME = "bitmap.png";

    public static void defaultCompressStream(WeakReference<Bitmap> bitmapWeakReference, Context context) {
        defaultCompressStream(bitmapWeakReference.get(), context);
    }

    private static void defaultCompressStream(Bitmap bitmap, Context context) {
        try {
            FileOutputStream stream = context.openFileOutput(DEFAULT_COMPRESSED_FILE_NAME, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.close();
        }  catch (IOException fexp) {
            Log.e("EXCP_COMPRESS_BITMAP", fexp.getMessage());
        }
    }

    public static Bitmap defaultDecompressStream(Context context) {
        Bitmap bitmap = null;
        try {
            FileInputStream fis = context.openFileInput(DEFAULT_COMPRESSED_FILE_NAME);
            bitmap = BitmapFactory.decodeStream(fis);
        } catch (IOException ioex) {
            Log.e("EXCP_DECOMPRESS_BITMAP", ioex.getMessage());
        }
        return bitmap;
    }

    public static byte [] compress(Bitmap bitmap, CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat.getType(), quality, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static WeakReference<Bitmap> decodeByteArray(byte [] buffer) {
        return new WeakReference<>(BitmapFactory.decodeByteArray(buffer, 0, buffer.length));
    }
}
